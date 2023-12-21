package buzzspire.helpdesk.dto.request.TicketPriority;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@NotNull
public class TicketPriorityRequest {
    private String priority;
}

