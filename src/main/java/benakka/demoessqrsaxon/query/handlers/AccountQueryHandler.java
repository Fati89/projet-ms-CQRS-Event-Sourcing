package benakka.demoessqrsaxon.query.handlers;

import benakka.demoessqrsaxon.query.dto.AccountStatementResponseDTO;
import benakka.demoessqrsaxon.query.entities.Account;
import benakka.demoessqrsaxon.query.entities.AccountOperation;
import benakka.demoessqrsaxon.query.queries.GetAccountStatementQuery;
import benakka.demoessqrsaxon.query.queries.GetAllAccountsQuery;
import benakka.demoessqrsaxon.query.repository.AccountRepository;
import benakka.demoessqrsaxon.query.repository.OperationRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountQueryHandler {
    private AccountRepository accountRepository;
    private OperationRepository operationRepository;

    public AccountQueryHandler(AccountRepository accountRepository, OperationRepository operationRepository) {
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
    }

    @QueryHandler
    public List<Account> on(GetAllAccountsQuery query){
        return accountRepository.findAll();
    }

    @QueryHandler
    public AccountStatementResponseDTO on(GetAccountStatementQuery query){
       Account account = accountRepository.findById(query.getAccountId()).get();
        List<AccountOperation> operations = operationRepository.findByAccountId(query.getAccountId());
        return new AccountStatementResponseDTO(account, operations);
    }


}
