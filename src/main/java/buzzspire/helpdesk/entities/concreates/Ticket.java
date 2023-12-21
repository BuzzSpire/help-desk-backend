package buzzspire.helpdesk.entities.concreates;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tickets")
public class Ticket {
    @GeneratedValue(generator = "increment")
    @Id
    private long id;
    private String title;
    private String description;
    @ManyToOne
    private TicketStatus status;
    @ManyToOne
    private TicketPriority priority;
    private Date date;
    @ManyToOne
    private User user;
    @ManyToOne
    private User assignedTo;
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<TicketMessage> messages;

}