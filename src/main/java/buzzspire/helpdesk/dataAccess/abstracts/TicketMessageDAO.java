package buzzspire.helpdesk.dataAccess.abstracts;

import buzzspire.helpdesk.entities.concreates.TicketMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketMessageDAO extends JpaRepository<TicketMessage, Long> {
    List<TicketMessage> findAllByTicketId(long id);
}
