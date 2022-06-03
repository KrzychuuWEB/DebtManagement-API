package pl.krzychuuweb.debtmanagment.debtor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface DebtorQueryRepository extends JpaRepository<Debtor, Long> {
    Optional<Debtor> findById(Long id);
}
