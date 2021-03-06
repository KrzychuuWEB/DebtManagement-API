package pl.krzychuuweb.debtmanagment.debtor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.krzychuuweb.debtmanagment.debtor.dto.DebtorCreateDTO;
import pl.krzychuuweb.debtmanagment.debtor.dto.DebtorDTO;
import pl.krzychuuweb.debtmanagment.debtor.dto.DebtorEditDTO;
import pl.krzychuuweb.debtmanagment.exception.BadRequestException;

import javax.validation.Valid;
import java.util.List;

import static pl.krzychuuweb.debtmanagment.debtor.dto.DebtorDTO.mapDebtorListToDebtorDTO;
import static pl.krzychuuweb.debtmanagment.debtor.dto.DebtorDTO.mapDebtorToDebtorDTO;
import static pl.krzychuuweb.debtmanagment.debtor.dto.DebtorMapper.mapDebtorCreateDTOToDebtor;
import static pl.krzychuuweb.debtmanagment.debtor.dto.DebtorMapper.mapDebtorEditDTOToDebtor;

@RestController
@RequestMapping("/debtors")
class DebtorController {

    private final DebtorQueryFacade debtorQueryFacade;
    private final DebtorFacade debtorFacade;

    DebtorController(final DebtorQueryFacade debtorQueryFacade, final DebtorFacade debtorFacade) {
        this.debtorQueryFacade = debtorQueryFacade;
        this.debtorFacade = debtorFacade;
    }

    @GetMapping
    List<DebtorDTO> getAllDebtors() {
        return mapDebtorListToDebtorDTO(debtorQueryFacade.getAllDebtors());
    }

    @GetMapping("/{id}")
    DebtorDTO getDebtor(@PathVariable Long id) {
        return mapDebtorToDebtorDTO(debtorQueryFacade.getDebtorById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    DebtorDTO createDebtor(@Valid @RequestBody DebtorCreateDTO debtorCreateDTO) {
        return mapDebtorToDebtorDTO(
                debtorFacade.addDebtor(
                        mapDebtorCreateDTOToDebtor(debtorCreateDTO)
                )
        );
    }

    @PutMapping("/{id}")
    DebtorDTO editDebtor(@PathVariable Long id, @Valid @RequestBody DebtorEditDTO debtorEditDTO) {
        if (!id.equals(debtorEditDTO.id())) {
            throw new BadRequestException("Id in path is not equals in body");
        }

        return mapDebtorToDebtorDTO(
                debtorFacade.editDebtor(
                        mapDebtorEditDTOToDebtor(debtorEditDTO)
                )
        );
    }

    @DeleteMapping("/{id}")
    void deleteDebtor(@PathVariable Long id) {
        debtorFacade.deleteDebtor(id);
    }
}
