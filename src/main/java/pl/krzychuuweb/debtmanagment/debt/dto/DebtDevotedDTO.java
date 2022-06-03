package pl.krzychuuweb.debtmanagment.debt.dto;

import pl.krzychuuweb.debtmanagment.debt.Debt;

public record DebtDevotedDTO(Long id, boolean devoted) {

    public static DebtDevotedDTO mapDebtToDebtDevotedDTO(Debt debt) {
        return new DebtDevotedDTO(debt.getId(), debt.isDevoted());
    }
}
