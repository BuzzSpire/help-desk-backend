package buzzspire.helpdesk.business.abstracts;

import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.entities.concreates.UserTitle;

import java.util.List;

public interface UserTitleServices {
    ResultData<List<UserTitle>> getAll();
    Result add(String title);
    Result update(long id, String title);
    Result delete(long id);
    UserTitle getByTitle(String title);
}
