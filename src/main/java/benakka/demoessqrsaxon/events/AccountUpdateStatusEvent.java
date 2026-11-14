package benakka.demoessqrsaxon.events;

import benakka.demoessqrsaxon.commands.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class AccountUpdateStatusEvent {
    private String accountId;
    private AccountStatus status;
}
