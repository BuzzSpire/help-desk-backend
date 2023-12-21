package buzzspire.helpdesk.api;

import buzzspire.helpdesk.business.abstracts.UserServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dto.request.auth.AuthLoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth APIs")
public class AuthController {

    // this is a field injection
    private final UserServices userServices;

    // this constructor is a dependency injection
    public AuthController(UserServices userServices) {
        this.userServices = userServices;
    }

    // this method login user and return token
    @Operation(summary = "Login")
    @PostMapping("/")
    public ResultData<String> login(@RequestBody AuthLoginRequest request) {
        return userServices.login(request.getEmail(), request.getPassword());
    }

    // this method verify token and return result
    @Operation(summary = "Verify Token")
    @GetMapping("/")
    public Result verifyToken(@RequestHeader String token) {
        return userServices.verifyToken(token);
    }
}
