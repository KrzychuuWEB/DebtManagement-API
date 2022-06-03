package pl.krzychuuweb.debtmanagment.debtor.dto;

import pl.krzychuuweb.debtmanagment.debtor.Debtor;

import java.util.List;
import java.util.stream.Collectors;

public record DebtorDTO(Long id, String firstName, String lastName) {
    public static List<DebtorDTO> mapDebtorListToDebtorDTO(List<Debtor> debtors) {
        return debtors.stream().map(debtor -> new DebtorDTO(debtor.getId(), debtor.getFirstName(), debtor.getLastName())).collect(Collectors.toList());
    }

    public static DebtorDTO mapDebtorToDebtorDTO(Debtor debtor) {
        return new DebtorDTO(debtor.getId(), debtor.getFirstName(), debtor.getLastName());
    }
}
