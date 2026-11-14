package benakka.demoessqrsaxon.commands.aggregates;

import benakka.demoessqrsaxon.commands.commands.AddAccountCommand;
import benakka.demoessqrsaxon.commands.commands.CreditAccountCommand;
import benakka.demoessqrsaxon.commands.commands.DebitAccountCommand;
import benakka.demoessqrsaxon.commands.commands.UpdateStatusCommand;
import benakka.demoessqrsaxon.commands.enums.AccountStatus;
import benakka.demoessqrsaxon.events.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private double balance;
    private AccountStatus status;

    public AccountAggregate(){}

    @CommandHandler // celle la est une fonction de decision car elle applique les regles metiers
    public AccountAggregate(AddAccountCommand command){
        log.info("############## AddAccountCommand Received ##############");
        if (command.getInitialBalance()<=0) throw new IllegalArgumentException("Balance must be positive");
        AggregateLifecycle.apply(new AccountCreatedEvent( // apply : 1.Identifie les méthodes @EventSourcingHandler correspondant à son type   2.appliquer l'événement à l'Aggregate en déclenchant le handler d'événement correspondant   3.l'enregistrer à l'événement dans l'Event Store
                command.getId(),
                command.getInitialBalance(),
                AccountStatus.CREATED,
                command.getCurrency()
        ));
        AggregateLifecycle.apply(new AccountActivatedEvent( // apply : 1.Identifie les méthodes @EventSourcingHandler correspondant à son type   2.appliquer l'événement à l'Aggregate en déclenchant le handler d'événement correspondant   3.l'enregistrer à l'événement dans l'Event Store
                command.getId(),
                AccountStatus.ACTIVATED
        ));
    }
    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        log.info("############## AccountCreatedEvent Occured ##############");
        this.accountId = event.getAccountId();
        this.balance = event.getInitialBalance();
        this.status = event.getStatus();
    }
    @EventSourcingHandler
    public void on(AccountActivatedEvent event){
        log.info("############## AccountActivatedEvent Occured ##############");
        this.accountId = event.getAccountId();
        this.status = event.getStatus();
    }



    @CommandHandler // celle la est une fonction de decision car elle applique les regles metiers
    public void handle(CreditAccountCommand command){
        log.info("############## CreditAccountCommand Received ##############");
        if (!status.equals(AccountStatus.ACTIVATED)) throw new RuntimeException("The account "+command.getId()+" is not activated");
        if (command.getAmount()<=0) throw new IllegalArgumentException("Amount must be positive");
        AggregateLifecycle.apply(new AccountCreditedEvent( // apply : 1.Identifie les méthodes @EventSourcingHandler correspondant à son type   2.appliquer l'événement à l'Aggregate en déclenchant le handler d'événement correspondant   3.l'enregistrer à l'événement dans l'Event Store
                command.getId(),
                command.getAmount(),
                command.getCurrency()
        ));
    }
    @EventSourcingHandler
    public void on(AccountCreditedEvent event){
        log.info("############## AccountCreditedEvent Occured ##############");
        this.accountId = event.getAccountId();
        this.balance = this.balance + event.getAmount();
    }


    @CommandHandler // celle la est une fonction de decision car elle applique les regles metiers
    public void handle(DebitAccountCommand command){
        log.info("############## DebitAccountCommand Received ##############");
        if (!status.equals(AccountStatus.ACTIVATED)) throw new RuntimeException("The account "+command.getId()+" is not activated");
        if (balance<command.getAmount()) throw new RuntimeException("Balance Not sufficient Exception");
        if (command.getAmount()<=0) throw new IllegalArgumentException("Amount must be positive");
        AggregateLifecycle.apply(new AccountDebitedEvent( // apply : 1.Identifie les méthodes @EventSourcingHandler correspondant à son type   2.appliquer l'événement à l'Aggregate en déclenchant le handler d'événement correspondant   3.l'enregistrer à l'événement dans l'Event Store
                command.getId(),
                command.getAmount(),
                command.getCurrency()
        ));
    }
    @EventSourcingHandler
    public void on(AccountDebitedEvent event){
        log.info("############## AccountDebitedEvent Occured ##############");
        this.accountId = event.getAccountId();
        this.balance = this.balance - event.getAmount();
    }


    @CommandHandler // celle la est une fonction de decision car elle applique les regles metiers
    public void handle(UpdateStatusCommand command){
        log.info("############## UpdateStatusCommand Received ##############");
        if (status.equals(command.getStatus())) throw new RuntimeException("This account "+command.getId()+" is alreeady "+command.getStatus());
        AggregateLifecycle.apply(new AccountUpdateStatusEvent( // apply : 1.Identifie les méthodes @EventSourcingHandler correspondant à son type   2.appliquer l'événement à l'Aggregate en déclenchant le handler d'événement correspondant   3.l'enregistrer à l'événement dans l'Event Store
                command.getId(),
                command.getStatus()
        ));
    }
    @EventSourcingHandler
    public void on(AccountUpdateStatusEvent event){
        log.info("############## AccountUpdateStatusEvent Occured ##############");
        this.accountId = event.getAccountId();
        this.status = event.getStatus();
    }
}
