package pl.krzychuuweb.debtmanagment.debtor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface DebtorQueryRepository extends JpaRepository<Debtor, Long> {
    Debtor getById(Long id);
}
