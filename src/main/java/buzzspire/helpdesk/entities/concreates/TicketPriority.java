package buzzspire.helpdesk.entities.concreates;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "priorities")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketPriority {
    @GeneratedValue(generator = "increment")
    @Id
    private long id;
    private String priority;
}
