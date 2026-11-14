package benakka.demoessqrsaxon.commands.dto;

public record DebitAccountRequestDTO(String accountId, double amount, String currency) {
}
