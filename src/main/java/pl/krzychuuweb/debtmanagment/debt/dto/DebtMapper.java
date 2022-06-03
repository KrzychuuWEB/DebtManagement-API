package pl.krzychuuweb.debtmanagment.debt.dto;

import pl.krzychuuweb.debtmanagment.debt.Debt;

public class DebtMapper {

    public static Debt mapDebtCreateDTOToDebt(DebtCreateDTO debtCreateDTO) {
        Debt debt = new Debt();
        debt.setName(debtCreateDTO.name());
        debt.setDescription(debtCreateDTO.description());
        debt.setPrice(debtCreateDTO.price());

        return debt;
    }

    public static Debt mapDebtEditDTOToDebt(DebtEditDTO debtEditDTO) {
        Debt debt = new Debt();
        debt.setId(debtEditDTO.id());
        debt.setName(debtEditDTO.name());
        debt.setDescription(debtEditDTO.description());
        debt.setPrice(debtEditDTO.price());
        return debt;
    }

    public static Debt mapDebtDevotedDTOToDebt(DebtDevotedDTO debtDevotedDTO) {
        Debt debt = new Debt();
        debt.setId(debtDevotedDTO.id());
        debt.setDevoted(debtDevotedDTO.devoted());
        return debt;
    }
}
