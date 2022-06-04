package pl.krzychuuweb.debtmanagment.debtor.dto;

import pl.krzychuuweb.debtmanagment.debtor.Debtor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DebtorDTO(
        @Min(1)
        Long id,
        @NotBlank
        @Size(min = 3, max = 255)
        String firstName,
        @NotBlank
        @Size(min = 3, max = 255)
        String lastName,
        LocalDateTime createdAt
) {
    public static List<DebtorDTO> mapDebtorListToDebtorDTO(List<Debtor> debtors) {
        return debtors.stream().map(debtor -> new DebtorDTO(debtor.getId(), debtor.getFirstName(), debtor.getLastName(), debtor.getCreatedAt())).collect(Collectors.toList());
    }

    public static DebtorDTO mapDebtorToDebtorDTO(Debtor debtor) {
        return new DebtorDTO(debtor.getId(), debtor.getFirstName(), debtor.getLastName(), debtor.getCreatedAt());
    }
}
