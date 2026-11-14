package benakka.demoessqrsaxon.query.repository;

import benakka.demoessqrsaxon.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}