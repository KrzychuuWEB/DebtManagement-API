package pl.krzychuuweb.debtmanagment.debtor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DebtorQueryFacadeTest {
    @Autowired
    private DebtorQueryRepository debtorQueryRepository;
    private DebtorQueryFacade debtorQueryFacade;

    @BeforeEach
    void setUp() {
        debtorQueryRepository = mock(DebtorQueryRepository.class);
        debtorQueryFacade = new DebtorQueryFacade(debtorQueryRepository);
    }

    @Test
    void should_get_debtor_by_id() {
        Debtor debtor = TestDebtorBuilder.newDebtor().build();
        when(debtorQueryRepository.getById(anyLong())).thenReturn(debtor);

        Debtor result = debtorQueryFacade.getDebtorById(anyLong());

        assertThat(result.getFirstName()).isEqualTo(debtor.getFirstName());
        assertThat(result.isEnabled()).isEqualTo(debtor.isEnabled());
    }

    @Test
    void should_get_debtor_by_id_return_exception() {
        when(debtorQueryRepository.getById(anyLong())).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> debtorQueryFacade.getDebtorById(anyLong()));
    }

    @Test
    void should_get_debtor_is_enabled_is_true() {
        Debtor debtor = TestDebtorBuilder.newDebtor().build();
        when(debtorQueryRepository.getById(anyLong())).thenReturn(debtor);

        boolean result = debtorQueryFacade.checkIfDebtorIsEnabled(anyLong());

        assertThat(result).isTrue();
    }

    @Test
    void should_get_debtor_is_enabled_is_false_and_expect_exception() {
        Debtor debtor = TestDebtorBuilder.newDebtor().but().withIsEnabled(false).build();
        when(debtorQueryRepository.getById(anyLong())).thenReturn(debtor);

        assertThrows(IllegalArgumentException.class, () -> debtorQueryFacade.checkIfDebtorIsEnabled(anyLong()));
    }
}