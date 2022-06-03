package pl.krzychuuweb.debtmanagment.debtor.dto;

import pl.krzychuuweb.debtmanagment.debtor.Debtor;

public class DebtorMapper {

    public static Debtor mapDebtorCreateDTOToDebtor(DebtorCreateDTO debtorCreateDTO) {
        Debtor debtor = new Debtor();
        debtor.setFirstName(debtorCreateDTO.firstName());
        debtor.setLastName(debtorCreateDTO.lastName());
        return debtor;
    }

    public static Debtor mapDebtorDTOToDebtor(DebtorDTO debtorDTO) {
        Debtor debtor = new Debtor();
        debtor.setId(debtorDTO.id());
        debtor.setFirstName(debtorDTO.firstName());
        debtor.setLastName(debtorDTO.lastName());
        return debtor;
    }
}
