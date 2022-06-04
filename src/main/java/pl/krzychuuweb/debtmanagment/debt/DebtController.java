package pl.krzychuuweb.debtmanagment.debt;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.krzychuuweb.debtmanagment.debt.dto.DebtCreateDTO;
import pl.krzychuuweb.debtmanagment.debt.dto.DebtDTO;
import pl.krzychuuweb.debtmanagment.debt.dto.DebtDevotedDTO;
import pl.krzychuuweb.debtmanagment.debt.dto.DebtEditDTO;
import pl.krzychuuweb.debtmanagment.exception.BadRequestException;

import javax.validation.Valid;
import java.util.List;

import static pl.krzychuuweb.debtmanagment.debt.dto.DebtDTO.mapDebtListToDebtDTOList;
import static pl.krzychuuweb.debtmanagment.debt.dto.DebtDTO.mapDebtToDebtDTO;
import static pl.krzychuuweb.debtmanagment.debt.dto.DebtMapper.*;

@RestController
@RequestMapping("/debts")
class DebtController {

    private final DebtQueryFacade debtQueryFacade;
    private final DebtFacade debtFacade;

    DebtController(final DebtQueryFacade debtQueryFacade, final DebtFacade debtFacade) {
        this.debtQueryFacade = debtQueryFacade;
        this.debtFacade = debtFacade;
    }

    @GetMapping
    List<DebtDTO> getAllDebts() {
        return mapDebtListToDebtDTOList(debtQueryFacade.getAllDebts());
    }

    @GetMapping("/{id}")
    DebtDTO getDebtById(@PathVariable Long id) {
        return mapDebtToDebtDTO(debtQueryFacade.getDebtById(id));
    }

    @GetMapping("/debtors/{id}")
    List<DebtDTO> getAllDebtsByDebtorId(@PathVariable Long id) {
        return mapDebtListToDebtDTOList(debtQueryFacade.getAllDebtsByDebtorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    DebtDTO createDebt(@Valid @RequestBody DebtCreateDTO debtCreateDTO) {
        return mapDebtToDebtDTO(
                debtFacade.addDebt(mapDebtCreateDTOToDebt(debtCreateDTO), debtCreateDTO.debtorId())
        );
    }

    @PutMapping("/{id}")
    DebtDTO editDebt(@PathVariable Long id, @Valid @RequestBody DebtEditDTO debtEditDTO) {
        if (!id.equals(debtEditDTO.id())) {
            throw new BadRequestException("Id in path is not equals in body");
        }

        return mapDebtToDebtDTO(
                debtFacade.editDebt(
                        mapDebtEditDTOToDebt(debtEditDTO)
                )
        );
    }

    @DeleteMapping("/{id}")
    void deleteDebt(@PathVariable Long id) {
        debtFacade.deleteDebt(id);
    }

    @PutMapping("/{id}/devoted")
    DebtDevotedDTO changeDebtDevoted(@PathVariable Long id, @Valid @RequestBody DebtDevotedDTO debtDevotedDTO) {
        if (!id.equals(debtDevotedDTO.id())) {
            throw new BadRequestException("Id in path is not equals in body");
        }

        return DebtDevotedDTO.mapDebtToDebtDevotedDTO(debtFacade.changeDevoted(
                id,
                mapDebtDevotedDTOToDebt(debtDevotedDTO)
        ));
    }
}
