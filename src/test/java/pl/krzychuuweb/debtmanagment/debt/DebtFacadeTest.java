package pl.krzychuuweb.debtmanagment.debt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.krzychuuweb.debtmanagment.debtor.Debtor;
import pl.krzychuuweb.debtmanagment.debtor.DebtorQueryFacade;
import pl.krzychuuweb.debtmanagment.debtor.TestDebtorBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DebtFacadeTest {

    @Autowired
    private DebtRepository debtRepository;
    @Autowired
    private DebtQueryFacade debtQueryFacade;
    @Autowired
    private DebtorQueryFacade debtorQueryFacade;
    private DebtFacade debtFacade;

    @BeforeEach
    void setUp() {
        debtRepository = mock(DebtRepository.class);
        debtQueryFacade = mock(DebtQueryFacade.class);
        debtorQueryFacade = mock(DebtorQueryFacade.class);
        debtFacade = new DebtFacade(debtRepository, debtQueryFacade, debtorQueryFacade);
    }

    @Test
    void should_add_new_debt() {
        Debt debt = TestDebtBuilder.newDebt().build();
        Debtor debtor = TestDebtorBuilder.newDebtor().build();

        when(debtRepository.save(debt)).thenReturn(debt);
        when(debtorQueryFacade.getDebtorById(anyLong())).thenReturn(debtor);

        Debt result = debtFacade.addDebt(debt, debtor.getId());
        verify(debtRepository, times(1)).save(debt);

        assertThat(result.getId()).isEqualTo(debt.getId());
        assertThat(result.getName()).isEqualTo(debt.getName());
    }

    @Test
    void should_change_devoted_on_true() {
        Debt debt = TestDebtBuilder.newDebt().but().withIsDevoted(false).build();
        Debt debtToEditDevoted = TestDebtBuilder.newDebt().but().withIsDevoted(true).build();
        when(debtQueryFacade.getDebtById(anyLong())).thenReturn(debt);

        Debt result = debtFacade.changeDevoted(debt.getId(), debtToEditDevoted);
        verify(debtRepository, times(1)).save(debt);

        assertThat(result.isDevoted()).isTrue();
    }

    @Test
    void should_change_devoted_on_false() {
        Debt debt = TestDebtBuilder.newDebt().but().withIsDevoted(true).build();
        Debt debtToEditDevoted = TestDebtBuilder.newDebt().but().withIsDevoted(false).build();
        when(debtQueryFacade.getDebtById(anyLong())).thenReturn(debt);

        Debt result = debtFacade.changeDevoted(debt.getId(), debtToEditDevoted);
        verify(debtRepository, times(1)).save(debt);

        assertThat(result.isDevoted()).isFalse();
    }

    @Test
    void should_edit_debt() {
        Debt debt = TestDebtBuilder.newDebt().build();
        Debt debtToEdit = TestDebtBuilder.newDebt().but().withId(debt.getId()).withName("EditName").withDescription("EditDescription").build();
        when(debtQueryFacade.getDebtById(anyLong())).thenReturn(debt);

        when(debtRepository.save(debt)).thenReturn(debt);
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