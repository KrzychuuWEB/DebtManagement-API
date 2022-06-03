package pl.krzychuuweb.debtmanagment.debtor;

import org.springframework.stereotype.Service;
import pl.krzychuuweb.debtmanagment.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class DebtorQueryFacade {

    private final DebtorQueryRepository debtorQueryRepository;

    public DebtorQueryFacade(final DebtorQueryRepository debtorQueryRepository) {
        this.debtorQueryRepository = debtorQueryRepository;
    }

    public List<Debtor> getAllDebtors() {
        return debtorQueryRepository.findAll();
    }

    public Debtor getDebtorById(Long id) {
        return debtorQueryRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Debtor with id "+ id +" not found!")
        );
    }

    public boolean checkIfDebtorIsEnabled(Long id) {
        if (!getDebtorById(id).isEnabled()) {
            throw new IllegalArgumentException("This debtor already not active");
        }

        return true;
    }
}
