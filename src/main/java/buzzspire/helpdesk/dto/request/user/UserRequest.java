package buzzspire.helpdesk.dto.request.user;

import buzzspire.helpdesk.entities.concreates.RoleEnum;
import buzzspire.helpdesk.entities.concreates.UserTitle;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@NotNull
public class UserRequest {
    private String name;
    private String surname;
    private String email;
    private String password;
    private RoleEnum role;
    private UserTitle title;
}
