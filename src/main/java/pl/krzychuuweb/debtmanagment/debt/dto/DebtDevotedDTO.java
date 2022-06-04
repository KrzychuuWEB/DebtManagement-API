package pl.krzychuuweb.debtmanagment.debt.dto;

import pl.krzychuuweb.debtmanagment.debt.Debt;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record DebtDevotedDTO(
        @Min(1)
        Long id,
        @NotNull
        boolean devoted
) {

    public static DebtDevotedDTO mapDebtToDebtDevotedDTO(Debt debt) {
        return new DebtDevotedDTO(debt.getId(), debt.isDevoted());
    }
}
