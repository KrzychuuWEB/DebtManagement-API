package pl.krzychuuweb.debtmanagment.debt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface DebtQueryRepository extends JpaRepository<Debt, Long> {

    Optional<Debt> findById(Long id);
    List<Debt> findAll();
}
