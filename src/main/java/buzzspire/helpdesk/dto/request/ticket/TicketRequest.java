package buzzspire.helpdesk.dto.request.ticket;

import buzzspire.helpdesk.entities.concreates.TicketStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@NotNull
public class TicketRequest {
    private String title;
    private String description;
    private long status;
    private long priority;
}
