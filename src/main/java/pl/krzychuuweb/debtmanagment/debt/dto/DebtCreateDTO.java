package pl.krzychuuweb.debtmanagment.debt.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public record DebtCreateDTO(
        @NotBlank
        @Size(min = 3, max = 255)
        String name,
        String description,
        @Digits(integer = 7, fraction = 2)
        BigDecimal price,
        @Min(1)
        Long debtorId
) {
}
