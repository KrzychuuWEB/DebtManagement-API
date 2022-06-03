package pl.krzychuuweb.debtmanagment.debt;

import org.springframework.stereotype.Service;
import pl.krzychuuweb.debtmanagment.exception.NotFoundException;

import java.util.List;

@Service
public class DebtQueryFacade {

    private final DebtQueryRepository debtQueryRepository;

    public DebtQueryFacade(final DebtQueryRepository debtQueryRepository) {
        this.debtQueryRepository = debtQueryRepository;
    }

    public Debt getDebtById(Long id) {
        return debtQueryRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Debt with id " + id + " is not exists")
        );
    }

    public List<Debt> getAllDebts() {
        return debtQueryRepository.findAll();
    }
}
