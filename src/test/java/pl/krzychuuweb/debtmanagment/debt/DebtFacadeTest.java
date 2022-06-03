package pl.krzychuuweb.debtmanagment.debt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DebtFacadeTest {

    @Autowired
    private DebtRepository debtRepository;
    @Autowired
    private DebtQueryFacade debtQueryFacade;
    private DebtFacade debtFacade;

    @BeforeEach
    void setUp() {
        debtRepository = mock(DebtRepository.class);
        debtQueryFacade = mock(DebtQueryFacade.class);
        debtFacade = new DebtFacade(debtRepository, debtQueryFacade);
    }

    @Test
    void should_add_new_debt() {
        Debt debt = TestDebtBuilder.newDebt().build();
        when(debtRepository.save(debt)).thenReturn(debt);

        Debt result = debtFacade.addDebt(debt);
        verify(debtRepository, times(1)).save(debt);

        assertThat(result.getId()).isEqualTo(debt.getId());
        assertThat(result.getName()).isEqualTo(debt.getName());
    }

    @Test
    void should_pay_back_the_debt() {
        Debt debt = TestDebtBuilder.newDebt().build();
        when(debtQueryFacade.getDebtById(anyLong())).thenReturn(debt);

        Debt result = debtFacade.payBackTheDebt(debt);
        verify(debtRepository, times(1)).save(debt);

        assertThat(result.isDevoted()).isTrue();
    }

    @Test
    void should_edit_debt() {
        Debt debt = TestDebtBuilder.newDebt().build();
        Debt debtToEdit = TestDebtBuilder.newDebt().but().withId(debt.getId()).withName("EditName").withDescription("EditDescription").build();
        when(debtQueryFacade.getDebtById(anyLong())).thenReturn(debt);

        Debt result = debtFacade.editDebt(debtToEdit);

        assertThat(result.getId()).isEqualTo(debtToEdit.getId());
        assertThat(result.getName()).isEqualTo(debtToEdit.getName());
        assertThat(result.getDescription()).isEqualTo(debtToEdit.getDescription());
    }

    @Test
    void should_delete_debt_by_id() {
        debtFacade.deleteDebt(anyLong());
        verify(debtRepository, times(1)).deleteDebtById(anyLong());
    }
}