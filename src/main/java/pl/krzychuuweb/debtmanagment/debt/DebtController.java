package pl.krzychuuweb.debtmanagment.debt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/debts")
class DebtController {

    private final DebtQueryFacade debtQueryFacade;

    DebtController(final DebtQueryFacade debtQueryFacade) {
        this.debtQueryFacade = debtQueryFacade;
    }

    @GetMapping
    List<Debt> getAllDebts() {
        return debtQueryFacade.getAllDebts();
    }
}
