package buzzspire.helpdesk.business.abstracts;

import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dto.request.user.UpdateUserBasicInfo;
import buzzspire.helpdesk.dto.request.user.UpdateUserPasswordRequest;
import buzzspire.helpdesk.dto.request.user.UserRequest;
import buzzspire.helpdesk.entities.concreates.User;

import java.util.List;

public interface UserServices {
    Result add(UserRequest user, String token);
    Result delete(long id, String token);
    Result updateBasicInfo(UpdateUserBasicInfo user, String token);
    Result updatePassword(UpdateUserPasswordRequest password, String token);
    ResultData<List<User>> getAll(String token);
    ResultData<User> getById(long id, String token);
    ResultData<String> login(String email, String password);
    Result verifyToken(String token);
    ResultData<User> getByEmail(String email);
    ResultData<List<User>> getAllSupporters(String token);
    ResultData<User> getUser(String token);
}
