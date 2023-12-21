package buzzspire.helpdesk.api;

import buzzspire.helpdesk.business.abstracts.UserTitleServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dto.request.UserTitle.UserTitleRequest;
import buzzspire.helpdesk.entities.concreates.UserTitle;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/titles")
@Tag(name = "User Title APIs")
public class UserTitleController {

    // this is field injection
    private final UserTitleServices userTitleServices;

    // this is constructor injection
    public UserTitleController(UserTitleServices userTitleServices) {
        this.userTitleServices = userTitleServices;
    }

    // this method is used to get all user titles
    @Operation(summary = "Get All User Titles")
    @GetMapping("/")
    public ResultData<List<UserTitle>> getAll() {
        return userTitleServices.getAll();
    }

    // this method is used to add user title
    @Operation(summary = "Add User Title")
    @PostMapping("/")
    public Result add(@RequestBody UserTitleRequest request) {
        System.out.println(request.getTitle());
        return userTitleServices.add(request.getTitle());
    }

    // this method is used to update user title
    @Operation(summary = "Update User Title")
    @PutMapping("/{id}")
    public Result update(@PathVariable int id, @RequestBody UserTitleRequest request) {
        return userTitleServices.update(id, request.getTitle());
    }

    // this method is used to delete user title
    @Operation(summary = "Delete User Title")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id) {
        return userTitleServices.delete(id);
    }
}
