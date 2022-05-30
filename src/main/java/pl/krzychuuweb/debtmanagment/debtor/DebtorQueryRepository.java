package pl.krzychuuweb.debtmanagment.debtor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface DebtorQueryRepository extends JpaRepository<Debtor, Long> {
    Debtor getById(Long id);
}
