package buzzspire.helpdesk.dto.request.UserTitle;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@NotNull
public class UserTitleRequest {
    private String title;
}
