package benakka.demoessqrsaxon.query.entities;

import benakka.demoessqrsaxon.commands.enums.AccountStatus;
import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.*;
import java.time.Instant;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class Account {
    @Id
    private String id;
    private double balance;
    private Instant createdAt;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private String currency;

}
