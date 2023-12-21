package buzzspire.helpdesk.entities.concreates;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ticket_status")
public class TicketStatus {
    @GeneratedValue(generator = "increment")
    @Id
    private long id;
    private String status;
}
