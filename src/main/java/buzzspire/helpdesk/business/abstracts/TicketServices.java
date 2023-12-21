package buzzspire.helpdesk.business.abstracts;

import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dto.request.ticket.TicketRequest;
import buzzspire.helpdesk.entities.concreates.Ticket;
import buzzspire.helpdesk.entities.concreates.TicketStatus;

import java.util.List;

public interface TicketServices {
    Result addTicket(TicketRequest ticket, String token);
    Result deleteTicket(long id, String token);
    ResultData<List<Ticket>> getAllTicket(String token, int page, int size);
    ResultData<Ticket> getTicketById(long id, String token);
    ResultData<List<Ticket>> getTicketByUserId(long id, int size, int page);
    Result addTicketAssignee(long ticketId, long assigneeId, String token);
    Result removeTickerAssignee(long ticketId, String token);
    Result closeTicket(long ticketId, String token);
    ResultData<List<Ticket>> getAllMyTicket(String token);
    Result updateTicketStatus(long ticketId, String token, long statusId);
}
