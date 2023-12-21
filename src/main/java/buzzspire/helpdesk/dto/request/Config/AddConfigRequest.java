package buzzspire.helpdesk.dto.request.Config;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@NotNull
public class AddConfigRequest {
    private long openTicketStatusId;
    private long closeTicketStatusId;
}
