package buzzspire.helpdesk.business.abstracts;

import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.entities.concreates.TicketStatus;

import java.util.List;

public interface TicketStatusServices {
    ResultData<List<TicketStatus>> getAll(String token);
    Result add(String token,String status);
    Result delete(String token,long id);

}
