package pl.krzychuuweb.debtmanagment.debt;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DebtFacade {

    private final DebtRepository debtRepository;
    private final DebtQueryFacade debtQueryFacade;

    public DebtFacade(final DebtRepository debtRepository, final DebtQueryFacade debtQueryFacade) {
        this.debtRepository = debtRepository;
        this.debtQueryFacade = debtQueryFacade;
    }

    public Debt addDebt(Debt debt) {
        return debtRepository.save(debt);
    }

    @Transactional
    public Debt payBackTheDebt(Debt debt) {
        Debt debtEdited = debtQueryFacade.getDebtById(debt.getId());
        debtEdited.setDevoted(true);

        debtRepository.save(debtEdited);

        return debtEdited;
    }

    public void deleteDebt(Long id) {
        debtRepository.deleteDebtById(id);
    }
}
