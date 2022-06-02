package pl.krzychuuweb.debtmanagment.debtor;

import org.springframework.web.bind.annotation.*;
import pl.krzychuuweb.debtmanagment.debtor.dto.DebtorDTO;

import java.util.List;

import static pl.krzychuuweb.debtmanagment.debtor.DebtorMapper.mapDebtorListToDebtorDTO;
import static pl.krzychuuweb.debtmanagment.debtor.DebtorMapper.mapDebtorToDebtorDTO;

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

    @DeleteMapping("/{id}")
    void deleteDebtor(@PathVariable Long id) {
        debtorFacade.deleteDebtor(id);
    }
}
