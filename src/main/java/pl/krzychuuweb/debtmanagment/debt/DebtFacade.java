package pl.krzychuuweb.debtmanagment.debt;

import org.springframework.stereotype.Service;
import pl.krzychuuweb.debtmanagment.debtor.DebtorQueryFacade;

import javax.transaction.Transactional;

@Service
public class DebtFacade {

    private final DebtRepository debtRepository;
    private final DebtQueryFacade debtQueryFacade;
    private final DebtorQueryFacade debtorQueryFacade;

    public DebtFacade(final DebtRepository debtRepository, final DebtQueryFacade debtQueryFacade, final DebtorQueryFacade debtorQueryFacade) {
        this.debtRepository = debtRepository;
        this.debtQueryFacade = debtQueryFacade;
        this.debtorQueryFacade = debtorQueryFacade;
    }

    public Debt addDebt(Debt debt, Long debtorId) {
        debt.setDebtor(debtorQueryFacade.getDebtorById(debtorId));

        return debtRepository.save(debt);
    }

    @Transactional
    public Debt changeDevoted(Long id, Debt debt) {
        Debt debtToChangeDevoted = debtQueryFacade.getDebtById(id);
        debtToChangeDevoted.setDevoted(debt.isDevoted());

        debtRepository.save(debtToChangeDevoted);

        return debtToChangeDevoted;
    }

    @Transactional
    public Debt editDebt(Debt debt) {
        Debt debtToEdit = debtQueryFacade.getDebtById(debt.getId());
        debtToEdit.setName(debt.getName());
        debtToEdit.setDescription(debt.getDescription());
        debtToEdit.setPrice(debt.getPrice());

        return debtRepository.save(debtToEdit);
    }

    public void deleteDebt(Long id) {
        debtRepository.deleteDebtById(id);
    }
}
