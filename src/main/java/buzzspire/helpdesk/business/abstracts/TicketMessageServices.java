package buzzspire.helpdesk.business.abstracts;

import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dto.request.TicketMessage.TicketMessageRequest;
import buzzspire.helpdesk.entities.concreates.TicketMessage;

import java.util.List;

public interface TicketMessageServices {
    ResultData<List<TicketMessage>> getAll();
    Result add(TicketMessageRequest TicketMessage, String token);
    Result delete(long id, String token);
    ResultData<TicketMessage> getById(long id);
}
