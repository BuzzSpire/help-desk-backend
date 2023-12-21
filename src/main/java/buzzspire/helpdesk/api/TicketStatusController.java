package buzzspire.helpdesk.api;

import buzzspire.helpdesk.business.abstracts.TicketStatusServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dto.request.TicketStatusRequest.TicketStatusRequest;
import buzzspire.helpdesk.entities.concreates.TicketStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Tag(name = "Ticket status APIs")
@RestController
@RequestMapping("/api/status")
public class TicketStatusController {

    // this is filed injection
    private final TicketStatusServices ticketStatusServices;

    // this is constructor injection
    public TicketStatusController(TicketStatusServices ticketStatusServices) {
        this.ticketStatusServices = ticketStatusServices;
    }

    // this is used get all ticket status
    @Operation(summary = "Get all ticket status")
    @GetMapping("/")
    public ResultData<List<TicketStatus>> getAll(@RequestHeader String token) {
        return ticketStatusServices.getAll(token);
    }

    // this is used post new ticket status
    @Operation(summary = "add new ticket status")
    @PostMapping("/")
    public Result add(@RequestHeader String token, @RequestBody TicketStatusRequest request) {
        return ticketStatusServices.add(token, request.getStatus());
    }

    // this is used delete ticket status
    @Operation(summary = "delete ticket status")
    @DeleteMapping("/{id}")
    public Result delete(@RequestHeader String token, @PathVariable long id) {
        return ticketStatusServices.delete(token, id);
    }
}
