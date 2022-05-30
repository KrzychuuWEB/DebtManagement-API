package pl.krzychuuweb.debtmanagment.debtor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class DebtorFacadeTest {

    @Autowired
    private DebtorRepository debtorRepository;
    @Autowired
    private DebtorQueryFacade debtorQueryFacade;
    private DebtorFacade debtorFacade;

    @BeforeEach
    void setUp() {
        debtorRepository = mock(DebtorRepository.class);
        debtorQueryFacade = mock(DebtorQueryFacade.class);
        debtorFacade = new DebtorFacade(debtorRepository, debtorQueryFacade);
    }

    @Test
    void should_add_new_debtor() {
        Debtor debtor = TestDebtorBuilder.newDebtor().build();
        when(debtorRepository.save(debtor)).thenReturn(debtor);

        Debtor result = debtorFacade.addDebtor(debtor);

        assertThat(result.getFirstName()).isEqualTo(debtor.getFirstName());
        assertThat(result.getId()).isEqualTo(debtor.getId());
    }

    @Test
    void should_edit_debtor() {
        Debtor debtor = TestDebtorBuilder.newDebtor().build();
        when(debtorQueryFacade.getDebtorById(anyLong())).thenReturn(debtor);
        debtor.setFirstName("EditExampleFirstName");

        when(debtorRepository.save(debtor)).thenReturn(debtor);

        Debtor result = debtorFacade.editDebtor(debtor);
        verify(debtorRepository, times(1)).save(debtor);

        assertThat(result.getFirstName()).isNotEqualTo(TestDebtorBuilder.DEFAULT_FIRSTNAME);
    }

    @Test
    void should_delete_debtor() {
        when(debtorQueryFacade.checkIfDebtorIsEnabled(anyLong())).thenReturn(true);

        debtorFacade.deleteDebtor(anyLong());
        verify(debtorRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void should_delete_debtor_return_exception() {
        when(debtorQueryFacade.checkIfDebtorIsEnabled(anyLong())).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> debtorFacade.deleteDebtor(anyLong()));
    }
}