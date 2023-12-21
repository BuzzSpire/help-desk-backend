package buzzspire.helpdesk.dataAccess.abstracts;

import buzzspire.helpdesk.entities.concreates.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketDAO extends JpaRepository<Ticket, Long> {
    Page<Ticket> findAllByUserId(long id, PageRequest pageRequest);
    List<Ticket> findAllByUserId(long id);
}
