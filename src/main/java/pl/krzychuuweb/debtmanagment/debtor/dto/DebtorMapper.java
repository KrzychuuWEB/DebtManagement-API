package pl.krzychuuweb.debtmanagment.debtor.dto;

import pl.krzychuuweb.debtmanagment.debtor.Debtor;

public class DebtorMapper {

    public static Debtor mapDebtorCreateDTOToDebtor(DebtorCreateDTO debtorCreateDTO) {
        Debtor debtor = new Debtor();
        debtor.setFirstName(debtorCreateDTO.firstName());
        debtor.setLastName(debtorCreateDTO.lastName());
        return debtor;
    }

    public static Debtor mapDebtorEditDTOToDebtor(DebtorEditDTO debtorEditDTO) {
        Debtor debtor = new Debtor();
        debtor.setId(debtorEditDTO.id());
        debtor.setFirstName(debtorEditDTO.firstName());
        debtor.setLastName(debtorEditDTO.lastName());
        return debtor;
    }
}
