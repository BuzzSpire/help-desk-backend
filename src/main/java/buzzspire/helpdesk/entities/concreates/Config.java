package buzzspire.helpdesk.entities.concreates;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "config")
public class Config {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @ManyToOne
    private TicketStatus openTicketStatus;
    @ManyToOne
    private TicketStatus closeTicketStatus;
}
