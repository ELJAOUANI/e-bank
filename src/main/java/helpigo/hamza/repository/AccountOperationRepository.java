package helpigo.hamza.repository;

import helpigo.hamza.entities.AccountOperations;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperations, Long> {
    List<AccountOperations> findByBankAccount_Id(String accountId);
}
