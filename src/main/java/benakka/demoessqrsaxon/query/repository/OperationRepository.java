package benakka.demoessqrsaxon.query.repository;

import benakka.demoessqrsaxon.query.entities.Account;
import benakka.demoessqrsaxon.query.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<AccountOperation, Long> {
    List<AccountOperation> findByAccountId(String id);
}