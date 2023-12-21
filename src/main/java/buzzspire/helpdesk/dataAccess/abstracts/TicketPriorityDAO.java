package buzzspire.helpdesk.dataAccess.abstracts;

import buzzspire.helpdesk.entities.concreates.TicketPriority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketPriorityDAO extends JpaRepository<TicketPriority,Long> {
}
