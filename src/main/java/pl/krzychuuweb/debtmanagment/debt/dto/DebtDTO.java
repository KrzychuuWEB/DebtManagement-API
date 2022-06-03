package pl.krzychuuweb.debtmanagment.debt.dto;

import pl.krzychuuweb.debtmanagment.debt.Debt;
import pl.krzychuuweb.debtmanagment.debtor.dto.DebtorDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public record DebtDTO(Long id, String name, String description, BigDecimal price, boolean isDevoted, DebtorDTO debtor) {

    public static List<DebtDTO> mapDebtListToDebtDTOList(List<Debt> debtList) {
        return debtList.stream().map(
                debt -> new DebtDTO(
                        debt.getId(),
                        debt.getName(),
                        debt.getDescription(),
                        debt.getPrice(),
                        debt.isDevoted(),
                        DebtorDTO.mapDebtorToDebtorDTO(debt.getDebtor())
                )
        ).collect(Collectors.toList());
    }

    public static DebtDTO mapDebtToDebtDTO(Debt debt) {
        return new DebtDTO(
                debt.getId(),
                debt.getName(),
                debt.getDescription(),
                debt.getPrice(),
                debt.isDevoted(),
                DebtorDTO.mapDebtorToDebtorDTO(debt.getDebtor())
        );
    }
}
