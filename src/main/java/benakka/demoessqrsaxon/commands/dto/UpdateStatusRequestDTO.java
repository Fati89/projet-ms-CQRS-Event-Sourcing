package benakka.demoessqrsaxon.commands.dto;

import benakka.demoessqrsaxon.commands.enums.AccountStatus;

public record UpdateStatusRequestDTO(String accountId, AccountStatus status) {
}
