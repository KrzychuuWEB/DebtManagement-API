package pl.krzychuuweb.debtmanagment.debt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface DebtQueryRepository extends JpaRepository<Debt, Long> {

    Debt getById(Long id);
    List<Debt> findAll();
}
