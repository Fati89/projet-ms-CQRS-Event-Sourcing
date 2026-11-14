package benakka.demoessqrsaxon.query.entities;

import benakka.demoessqrsaxon.commands.enums.OperationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Date;
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class AccountOperation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant date;
    private double amount;
    @Enumerated(EnumType.STRING)
    private OperationType type;
    private String currency;
    @ManyToOne
    private Account account;
}
