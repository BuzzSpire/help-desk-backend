package buzzspire.helpdesk.dataAccess.abstracts;

import buzzspire.helpdesk.entities.concreates.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketStatusDAO extends JpaRepository<TicketStatus, Long> {
}
