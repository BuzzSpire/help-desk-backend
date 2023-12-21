package buzzspire.helpdesk.dto.request.TicketMessage;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Getter
@NoArgsConstructor
@NotNull
public class TicketMessageRequest {
    private String message;
    private long ticketId;
}
