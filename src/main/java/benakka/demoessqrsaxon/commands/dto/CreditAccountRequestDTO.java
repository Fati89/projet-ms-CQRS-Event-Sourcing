package benakka.demoessqrsaxon.commands.dto;

public record CreditAccountRequestDTO(String accountId, double amount, String currency) {
}
