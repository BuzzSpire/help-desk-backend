package buzzspire.helpdesk.business.concreates;

import buzzspire.helpdesk.business.abstracts.UserServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dataAccess.abstracts.UserDAO;
import buzzspire.helpdesk.dto.request.user.UpdateUserBasicInfo;
import buzzspire.helpdesk.dto.request.user.UpdateUserPasswordRequest;
import buzzspire.helpdesk.dto.request.user.UserRequest;
import buzzspire.helpdesk.entities.concreates.RoleEnum;
import buzzspire.helpdesk.entities.concreates.User;
import buzzspire.helpdesk.security.JwtTokenProvider;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;

@Service
public class UserManager implements UserServices {

    // filed injection
    private final UserDAO userDAO;
    private final JwtTokenProvider jwtTokenProvider;

    // constructor injection
    public UserManager(UserDAO userDAO, JwtTokenProvider jwtTokenProvider) {
        this.userDAO = userDAO;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // this method used for add new user
    @Override
    public Result add(UserRequest user, String token) {
        String role = jwtTokenProvider.getRoleFromToken(token);
        if(!role.equals(RoleEnum.ADMIN.toString())){
            return new Result(false, "You are not authorized");
        }

        User u = userDAO.findByEmail(user.getEmail());

        if(u != null){
            return new Result(false, "User already exist");
        }

        User newUser = User.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .title(user.getTitle())
                .build();
        try {
            userDAO.save(newUser);
        } catch (Exception e) {
            return new Result(false, "User not added");
        }
        return new Result(true, "User added");
    }

    // this method used for delete user
    @Override
    public Result delete(long id, String token) {
        String role = jwtTokenProvider.getRoleFromToken(token);
        if(!role.equals(RoleEnum.ADMIN.toString())){
            return new Result(false, "You are not authorized");
        }

        try {
            userDAO.delete(userDAO.getUserById(id));
        } catch (Exception e) {
            return new Result(false, "User not deleted");
        }
        return new Result(true, "User deleted");
    }

    // this method used for update user basic info
    @Override
    public Result updateBasicInfo(UpdateUserBasicInfo user, String token) {
        if (!jwtTokenProvider.validateToken(token)) {
            return new Result(false, "Token is not valid");
        }
        long id = jwtTokenProvider.getIdFromToken(token);
        User newUser = userDAO.getUserById(id);
        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setEmail(user.getEmail());
        try {
            userDAO.save(newUser);
        } catch (Exception e) {
            return new Result(false, "User not updated");
        }
        return new Result(true, "User updated");
    }

    // this method used for update user password
    @Override
    public Result updatePassword(UpdateUserPasswordRequest request, String token) {
        if (!jwtTokenProvider.validateToken(token)) {
            return new Result(false, "Token is not valid");
        }
        long id = jwtTokenProvider.getIdFromToken(token);
        User newUser = userDAO.getUserById(id);
        newUser.setPassword(request.getPassword());
        try {
            userDAO.save(newUser);
        } catch (Exception e) {
            return new Result(false, "User not updated");
        }
        return new Result(true, "User updated");
    }

    // this method used for get all user
    @Override
    public ResultData<List<User>> getAll(String token) {
        String role = jwtTokenProvider.getRoleFromToken(token);
        if(!role.equals(RoleEnum.ADMIN.toString()) && !role.equals(RoleEnum.SUPPORT.toString())){
            return new ResultData<>(null, "You are not authorized", false);
        }
        return new ResultData<>(userDAO.findAll(), "All user list",true);
    }

    // this method used for get user by id
    @Override
    public ResultData<User> getById(long id, String token) {
        String role = jwtTokenProvider.getRoleFromToken(token);
        if(!role.equals(RoleEnum.ADMIN.toString()) && !role.equals(RoleEnum.SUPPORT.toString())){
            return new ResultData<>(null, "You are not authorized", false);
        }
        return new ResultData<>(userDAO.getUserById(id), "User", true);
    }

    // this method used for login
    @Override
    public ResultData<String> login(String email, String password) {
        User user = userDAO.findByEmailAndPassword(email, password);
        if(user == null ){
            return new ResultData<>(null, "email or password wrong", false);
        }
        String token = jwtTokenProvider.createToken(user);
        return new ResultData<>(token, "Login successful", true);
    }

    // this method used for verify token
    @Override
    public Result verifyToken(String token) {
        if(jwtTokenProvider.validateToken(token)){
            return new Result(true, "Token is valid");
        }
        return new Result(false, "Token is not valid");
    }

    // This method is only used within the application and is for getting the admin e-mail address.
    @Override
    public ResultData<User> getByEmail(String email) {
        return new ResultData<>(userDAO.findByEmail(email), "User", true);
    }

    // get user data by token
    @Override
    public ResultData<User> getUser(String token) {
        if (!jwtTokenProvider.validateToken(token)) {
            return new ResultData<>(null, "Token is not valid", false);
        }
        long id = jwtTokenProvider.getIdFromToken(token);
        return new ResultData<>(userDAO.getUserById(id), "User", true);
    }

    // this method used for get all supporter
    @Override
    public ResultData<List<User>> getAllSupporters(String token) {
        String role = jwtTokenProvider.getRoleFromToken(token);
        if(role.equals(RoleEnum.ADMIN.toString()) || role.equals(RoleEnum.SUPPORT.toString())){
            return new ResultData<>(userDAO.findAllByRole(RoleEnum.SUPPORT), "All supporter list", true);
        } else {
            return new ResultData<>(null, "You are not authorized", false);
        }
    }
}
