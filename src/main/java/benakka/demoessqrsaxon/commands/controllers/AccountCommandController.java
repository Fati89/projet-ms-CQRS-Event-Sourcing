package benakka.demoessqrsaxon.commands.controllers;

import benakka.demoessqrsaxon.commands.commands.AddAccountCommand;
import benakka.demoessqrsaxon.commands.commands.CreditAccountCommand;
import benakka.demoessqrsaxon.commands.commands.DebitAccountCommand;
import benakka.demoessqrsaxon.commands.commands.UpdateStatusCommand;
import benakka.demoessqrsaxon.commands.dto.AddNewAccountRequestDTO;
import benakka.demoessqrsaxon.commands.dto.CreditAccountRequestDTO;
import benakka.demoessqrsaxon.commands.dto.DebitAccountRequestDTO;
import benakka.demoessqrsaxon.commands.dto.UpdateStatusRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;
import org.axonframework.eventsourcing.eventstore.EventStore;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/commands/accounts")
public class AccountCommandController {

    private CommandGateway commandGateway; // commandGateway est un composant d'Axon qui permet d'envoyer une commande au gestionnaire correspondant (CommandHandler).
    private EventStore eventStore;
    public AccountCommandController(CommandGateway commandGateway, EventStore eventStore){
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
    }

    @PostMapping("/add")
    public CompletableFuture<String> addNewAccount(@RequestBody AddNewAccountRequestDTO request){
        CompletableFuture<String> response = commandGateway.send(new AddAccountCommand( // Le Gateway transmet la commande au CommandBus, ce dernier distribue les commandes aux CommandHandlers, Comment il trouve le bonne CommandeHndler ? : Axon détecte que - Il existe un Aggregate appelé AccountAggregate - Il possède un constructeur annoté @CommandHandler - Ce constructeur traite la commande AddAccountCommand.
                UUID.randomUUID().toString().toString(),
                request.initialBalance(),
                request.currency()
        ));
        return response;
    }
    @PostMapping("/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountRequestDTO request){
        CompletableFuture<String> response = commandGateway.send(new CreditAccountCommand( // Le Gateway transmet la commande au CommandBus, ce dernier distribue les commandes aux CommandHandlers, Comment il trouve le bonne CommandeHndler ? : Axon détecte que - Il existe un Aggregate appelé AccountAggregate - Il possède un constructeur annoté @CommandHandler - Ce constructeur traite la commande AddAccountCommand.
                request.accountId(),
                request.amount(),
                request.currency()
        ));
        return response;
    }
    @PostMapping("/debit")
    public CompletableFuture<String> debitAccount(@RequestBody DebitAccountRequestDTO request){
        CompletableFuture<String> response = commandGateway.send(new DebitAccountCommand( // Le Gateway transmet la commande au CommandBus, ce dernier distribue les commandes aux CommandHandlers, Comment il trouve le bonne CommandeHndler ? : Axon détecte que - Il existe un Aggregate appelé AccountAggregate - Il possède un constructeur annoté @CommandHandler - Ce constructeur traite la commande AddAccountCommand.
                request.accountId(),
                request.amount(),
                request.currency()
        ));
        return response;
    }

    @PutMapping("/updateStatus")
    public CompletableFuture<String> updateStatus(@RequestBody UpdateStatusRequestDTO request){
        CompletableFuture<String> response = commandGateway.send(new UpdateStatusCommand( // Le Gateway transmet la commande au CommandBus, ce dernier distribue les commandes aux CommandHandlers, Comment il trouve le bonne CommandeHndler ? : Axon détecte que - Il existe un Aggregate appelé AccountAggregate - Il possède un constructeur annoté @CommandHandler - Ce constructeur traite la commande AddAccountCommand.
                request.accountId(),
                request.status()
        ));
        return response;
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception exception){
        return exception.getMessage();
    }

    @GetMapping("/events/{accountId}")
    public Stream eventStore(@PathVariable String accountId){
        return eventStore.readEvents(accountId).asStream();
    }
}
