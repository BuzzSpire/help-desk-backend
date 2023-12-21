package buzzspire.helpdesk.business.concreates;

import buzzspire.helpdesk.business.abstracts.UserTitleServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dataAccess.abstracts.UserTitleDAO;
import buzzspire.helpdesk.entities.concreates.UserTitle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTitleManager implements UserTitleServices {

    // filed injection
    private final UserTitleDAO userTitleDAO;

    // constructor injection
    public UserTitleManager(UserTitleDAO userTitleDAO) {
        this.userTitleDAO = userTitleDAO;
    }

    // this method is used for get all user title
    @Override
    public ResultData<List<UserTitle>> getAll() {
        return new ResultData<>(userTitleDAO.findAll(), "user title lis", true);
    }

    // this method is used for add new user title
    @Override
    public Result add(String title) {
        try {
            userTitleDAO.save(UserTitle.builder().title(title).build());
        }
        catch (Exception e) {
            return new Result(false, "user title not added");
        }

        return new Result(true, "user title added");
    }

    // this method is used for update user title
    @Override
    public Result update(long id, String title) {
        UserTitle userTitle = userTitleDAO.findById(id).get();
        userTitle.setTitle(title);

        try {
            userTitleDAO.save(userTitle);
        }
        catch (Exception e) {
            return new Result(false, "user title not updated");
        }
        return new Result(true, "user title updated");
    }

    // this method is used for delete user title
    @Override
    public Result delete(long id) {
        try {
            userTitleDAO.deleteById(id);
        }
        catch (Exception e) {
            return new Result(false, "user title not deleted");
        }
        return new Result(true, "user title deleted");
    }

    // this method is used for get user title by id
    @Override
    public UserTitle getByTitle(String title) {
        return userTitleDAO.getByTitle(title);
    }
}
