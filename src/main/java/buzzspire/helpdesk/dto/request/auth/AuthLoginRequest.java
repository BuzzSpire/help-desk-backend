package buzzspire.helpdesk.dto.request.auth;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@NotNull
public class AuthLoginRequest {
    private String email;
    private String password;
}
