package pl.krzychuuweb.debtmanagment.debt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.krzychuuweb.debtmanagment.debtor.Debtor;
import pl.krzychuuweb.debtmanagment.debtor.TestDebtorBuilder;
import pl.krzychuuweb.debtmanagment.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DebtQueryFacadeTest {

    @Autowired
    private DebtQueryRepository debtQueryRepository;
    private DebtQueryFacade debtQueryFacade;

    @BeforeEach
    void setUp() {
        debtQueryRepository = mock(DebtQueryRepository.class);
        debtQueryFacade = new DebtQueryFacade(debtQueryRepository);
    }

    @Test
    void should_get_debt_by_id() {
        Debt debt = TestDebtBuilder.newDebt().build();
        when(debtQueryRepository.findById(anyLong())).thenReturn(Optional.of(debt));

        Debt result = debtQueryFacade.getDebtById(anyLong());

        assertThat(result.getName()).isEqualTo(debt.getName());
        assertThat(result.isDevoted()).isFalse();
    }

    @Test
    void should_get_debt_by_id_expect_exception() {
        when(debtQueryRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> debtQueryFacade.getDebtById(anyLong()));
    }

    @Test
    void should_get_all_debts() {
        List<Debt> debtList = new ArrayList<>();
        debtList.add(TestDebtBuilder.newDebt().build());
        debtList.add(TestDebtBuilder.newDebt().but().withId(2L).withName("AnyName").build());

        when(debtQueryRepository.findAll()).thenReturn(debtList);

        List<Debt> result = debtQueryFacade.getAllDebts();

        assertThat(result).hasSize(2);
    }

    @Test
    void should_get_all_debts_by_debtor_id() {
        Debtor debtor = TestDebtorBuilder.newDebtor().withId(1L).build();

        List<Debt> debtList = List.of(
                TestDebtBuilder.newDebt().withId(1L).withDebtor(debtor).build(),
                TestDebtBuilder.newDebt().withId(2L).withDebtor(debtor).build()
        );

        when(debtQueryRepository.findAllByDebtorId(debtor.getId())).thenReturn(debtList);

        List<Debt> result = debtQueryFacade.getAllDebtsByDebtorId(debtor.getId());

        assertThat(result).hasSize(2);
    }

    @Test
    void should_get_all_debts_by_debtor_id_return_exception() {
        when(debtQueryRepository.findAllByDebtorId(anyLong())).thenReturn(null);

        assertThrows(NotFoundException.class, () -> debtQueryFacade.getAllDebtsByDebtorId(anyLong()));
    }
}