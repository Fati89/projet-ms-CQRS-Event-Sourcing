package benakka.demoessqrsaxon.commands.controllers;

import benakka.demoessqrsaxon.commands.commands.AddAccountCommand;
import benakka.demoessqrsaxon.commands.dto.AddNewAccountRestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/accounts")
public class AccountCommandController {
    private CommandGateway commandGateway; // commandGateway est un composant d'Axon qui permet d'envoyer une commande au gestionnaire correspondant (CommandHandler).
    public AccountCommandController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @PostMapping("/add")
    public CompletableFuture<String> addNewAccount(@RequestBody AddNewAccountRestDTO request){
        CompletableFuture<String> response = commandGateway.send(new AddAccountCommand(
                UUID.randomUUID().toString().toString(),
                request.initialBalance(),
                request.currency()
        ));
        return response;
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception exception){
        return exception.getMessage();
    }
}
