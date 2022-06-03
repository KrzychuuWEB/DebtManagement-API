package pl.krzychuuweb.debtmanagment.debt;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DebtQueryFacade {

    private final DebtQueryRepository debtQueryRepository;

    public DebtQueryFacade(final DebtQueryRepository debtQueryRepository) {
        this.debtQueryRepository = debtQueryRepository;
    }

    public Debt getDebtById(Long id) {
        return debtQueryRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Debt with id "+ id +" is not exists")
        );
    }

    public List<Debt> getAllDebts() {
        return debtQueryRepository.findAll();
    }
}
