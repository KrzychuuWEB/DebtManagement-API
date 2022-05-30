package pl.krzychuuweb.debtmanagment.debtor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface DebtorRepository extends JpaRepository<Debtor, Long> {
    void deleteById(Long id);
}
