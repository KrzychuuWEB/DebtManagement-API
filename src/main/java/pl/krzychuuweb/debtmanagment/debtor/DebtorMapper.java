package pl.krzychuuweb.debtmanagment.debtor;

import pl.krzychuuweb.debtmanagment.debtor.dto.DebtorDTO;

import java.util.List;
import java.util.stream.Collectors;

class DebtorMapper {

    static List<DebtorDTO> mapDebtorListToDebtorDTO(List<Debtor> debtors) {
        return debtors.stream().map(debtor -> new DebtorDTO(debtor.getId(), debtor.getFirstName(), debtor.getLastName())).collect(Collectors.toList());
    }

    static DebtorDTO mapDebtorToDebtorDTO(Debtor debtor) {
        return new DebtorDTO(debtor.getId(), debtor.getFirstName(), debtor.getLastName());
    }
}
