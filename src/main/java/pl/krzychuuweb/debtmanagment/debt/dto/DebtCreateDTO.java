package pl.krzychuuweb.debtmanagment.debt.dto;

import java.math.BigDecimal;

public record DebtCreateDTO(String name, String description, BigDecimal price, Long debtorId) {
}
