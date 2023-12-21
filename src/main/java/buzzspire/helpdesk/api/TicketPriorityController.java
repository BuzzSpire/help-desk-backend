package buzzspire.helpdesk.api;

import buzzspire.helpdesk.business.abstracts.TicketPriorityServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dto.request.TicketPriority.TicketPriorityRequest;
import buzzspire.helpdesk.entities.concreates.TicketPriority;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Tag(name = "Ticket Priority APIs")
@RestController
@RequestMapping("/api/priority")
public class TicketPriorityController {

    // this is a field injection
    private final TicketPriorityServices ticketPriorityServices;

    // this is a constructor injection
    public TicketPriorityController(TicketPriorityServices ticketPriorityServices) {
        this.ticketPriorityServices = ticketPriorityServices;
    }

    // this method is used to get all ticket
    @Operation(summary = "Get all ticket priorities")
    @GetMapping("/")
    public ResultData<List<TicketPriority>> getAll(@RequestHeader String token) {
        return ticketPriorityServices.getAll(token);
    }

    // this method is used add ticket
    @Operation(summary = "Add ticket priority")
    @PostMapping("/")
    public Result add(@RequestHeader String token, @RequestBody TicketPriorityRequest request) {
        return ticketPriorityServices.add(token,request.getPriority());
    }

    // this method is used  delete ticket
    @Operation(summary = "Delete ticket priority")
    @DeleteMapping("/{id}")
    public Result delete(@RequestHeader String token, @PathVariable long id) {
        return ticketPriorityServices.delete(token,id);
    }

}
