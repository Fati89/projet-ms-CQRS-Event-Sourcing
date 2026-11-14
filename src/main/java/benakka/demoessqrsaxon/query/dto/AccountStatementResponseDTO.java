package benakka.demoessqrsaxon.query.dto;

import benakka.demoessqrsaxon.query.entities.Account;
import benakka.demoessqrsaxon.query.entities.AccountOperation;

import java.util.List;

public record AccountStatementResponseDTO(
        Account account, List<AccountOperation> operations
) {
}
