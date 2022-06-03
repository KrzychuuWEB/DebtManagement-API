package pl.krzychuuweb.debtmanagment.debt.dto;

import java.math.BigDecimal;

public record DebtEditDTO(Long id, String name, String description, BigDecimal price) {
}
