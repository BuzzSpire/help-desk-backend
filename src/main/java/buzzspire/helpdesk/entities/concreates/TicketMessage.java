package buzzspire.helpdesk.entities.concreates;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "messages")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class TicketMessage {
    @GeneratedValue(generator = "increment")
    @Id
    private long id;
    private String message;
    private Date date;
    @ManyToOne
    private User sender;
    @ManyToOne
    @JsonIgnoreProperties({"messages"})
    private Ticket ticket;
}