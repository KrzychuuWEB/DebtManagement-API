package pl.krzychuuweb.debtmanagment.debtor;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DebtorFacade {

    private final DebtorRepository debtorRepository;
    private final DebtorQueryFacade debtorQueryFacade;

    public DebtorFacade(final DebtorRepository debtorRepository, final DebtorQueryFacade debtorQueryFacade) {
        this.debtorRepository = debtorRepository;
        this.debtorQueryFacade = debtorQueryFacade;
    }

    public Debtor addDebtor(Debtor debtor) {
        return debtorRepository.save(debtor);
    }

    @Transactional
    public Debtor editDebtor(Debtor debtor) {
        Debtor debtorEdited = debtorQueryFacade.getDebtorById(debtor.getId());
        debtorEdited.setFirstName(debtor.getFirstName());
        debtorEdited.setLastName(debtor.getLastName());

        return debtorRepository.save(debtorEdited);
    }

    public void deleteDebtor(Long id) {
        debtorQueryFacade.checkIfDebtorIsEnabled(id);
        debtorRepository.deleteById(id);
    }
}
