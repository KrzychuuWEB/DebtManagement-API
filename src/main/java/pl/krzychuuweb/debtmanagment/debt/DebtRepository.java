package pl.krzychuuweb.debtmanagment.debt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface DebtRepository extends JpaRepository<Debt, Long> {

    void deleteDebtById(Long id);
}
