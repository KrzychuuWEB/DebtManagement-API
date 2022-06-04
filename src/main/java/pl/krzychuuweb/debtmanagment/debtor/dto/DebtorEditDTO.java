package pl.krzychuuweb.debtmanagment.debtor.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record DebtorEditDTO(
        @Min(1)
        Long id,
        @NotBlank
        @Size(min = 3, max = 255)
        String firstName,
        @NotBlank
        @Size(min = 3, max = 255)
        String lastName
) {
}
