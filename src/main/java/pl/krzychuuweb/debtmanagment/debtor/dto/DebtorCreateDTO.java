package pl.krzychuuweb.debtmanagment.debtor.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record DebtorCreateDTO(
        @NotBlank
        @Size(min = 3, max = 255)
        String firstName,
        @NotBlank
        @Size(min = 3, max = 255)
        String lastName
) {
}
