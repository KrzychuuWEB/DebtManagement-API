package pl.krzychuuweb.debtmanagment.debt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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
        when(debtQueryRepository.getById(anyLong())).thenReturn(debt);

        Debt result = debtQueryFacade.getDebtById(anyLong());

        assertThat(result.getName()).isEqualTo(debt.getName());
        assertThat(result.isDevoted()).isFalse();
    }

    @Test
    void should_get_debt_by_id_expect_exception() {
        when(debtQueryRepository.getById(anyLong())).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> debtQueryFacade.getDebtById(anyLong()));
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
}