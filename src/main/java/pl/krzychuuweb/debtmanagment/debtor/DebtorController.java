package pl.krzychuuweb.debtmanagment.debtor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.krzychuuweb.debtmanagment.debtor.dto.DebtorCreateDTO;
import pl.krzychuuweb.debtmanagment.debtor.dto.DebtorDTO;

import java.util.List;

import static pl.krzychuuweb.debtmanagment.debtor.dto.DebtorDTO.mapDebtorListToDebtorDTO;
import static pl.krzychuuweb.debtmanagment.debtor.dto.DebtorDTO.mapDebtorToDebtorDTO;
import static pl.krzychuuweb.debtmanagment.debtor.dto.DebtorMapper.mapDebtorCreateDTOToDebtor;
import static pl.krzychuuweb.debtmanagment.debtor.dto.DebtorMapper.mapDebtorDTOToDebtor;

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
    DebtorDTO createDebtor(@RequestBody DebtorCreateDTO debtorCreateDTO) {
        return mapDebtorToDebtorDTO(
                debtorFacade.addDebtor(
                        mapDebtorCreateDTOToDebtor(debtorCreateDTO)
                )
        );
    }

    @PutMapping("/{id}")
    DebtorDTO editDebtor(@PathVariable Long id, @RequestBody DebtorDTO debtorDTO) {
        return mapDebtorToDebtorDTO(
                debtorFacade.editDebtor(
                        mapDebtorDTOToDebtor(debtorDTO)
                )
        );
    }

    @DeleteMapping("/{id}")
    void deleteDebtor(@PathVariable Long id) {
        debtorFacade.deleteDebtor(id);
    }
}
