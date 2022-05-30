package pl.krzychuuweb.debtmanagment.debtor;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DebtorQueryFacade {

    private final DebtorQueryRepository debtorQueryRepository;

    public DebtorQueryFacade(final DebtorQueryRepository debtorQueryRepository) {
        this.debtorQueryRepository = debtorQueryRepository;
    }

    public Debtor getDebtorById(Long id) {
        return Optional.ofNullable(debtorQueryRepository.getById(id)).orElseThrow(
                () -> new IllegalArgumentException("Debtor with id "+ id +" not found!")
        );
    }

    public boolean checkIfDebtorIsEnabled(Long id) {
        if (!getDebtorById(id).isEnabled()) {
            throw new IllegalArgumentException("This debtor already not active");
        }

        return true;
    }
}
