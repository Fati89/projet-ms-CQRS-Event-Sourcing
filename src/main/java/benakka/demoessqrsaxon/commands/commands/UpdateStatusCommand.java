package benakka.demoessqrsaxon.commands.commands;

import benakka.demoessqrsaxon.commands.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter @AllArgsConstructor
public class UpdateStatusCommand {
    @TargetAggregateIdentifier
    private String id;
    private AccountStatus status;
}
