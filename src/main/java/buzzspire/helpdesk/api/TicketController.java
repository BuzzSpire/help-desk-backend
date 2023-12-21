package buzzspire.helpdesk.api;

import buzzspire.helpdesk.business.abstracts.TicketServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dto.request.ticket.TicketRequest;
import buzzspire.helpdesk.entities.concreates.Ticket;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Tag(name = "Ticket APIs")
@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    // this is a field injection
    private final TicketServices ticketServices;

    // this is a constructor injection
    public TicketController(TicketServices ticketServices) {
        this.ticketServices = ticketServices;
    }


    // this method is a get method and it is return all ticket
    @Operation(summary = "Get All Ticket")
    @GetMapping("/{page}/{size}")
    public ResultData<List<Ticket>> getTicket(@PathVariable int page, @PathVariable int size ,@RequestHeader String token) {
        return ticketServices.getAllTicket(token,page,size);
    }

    // this method is a post method and it is add ticket
    @Operation(summary = "Add Ticket")
    @PostMapping("/")
    public Result addTicket(@RequestHeader String token, @RequestBody TicketRequest request) {
        return ticketServices.addTicket(request, token);
    }

    // this method is a get method and it is return ticket by id
    @Operation(summary = "get Ticket By Id")
    @GetMapping("/{id}")
    public ResultData<Ticket> getTicketById(@RequestHeader String token ,@PathVariable long id) {
        return ticketServices.getTicketById(id,token);
    }

    // this method is a get method and it is return ticket by user id
    @Operation(summary = "get Ticket By User Id")
    @GetMapping("/user/{id}/{page}/{size}")
    public ResultData<List<Ticket>> getTicketByUserId(@PathVariable long id, @PathVariable int page, @PathVariable int size) {
        return ticketServices.getTicketByUserId(id, page, size);
    }

    // this method is a delete method and it is delete ticket by id
    @Operation(summary = "delete Ticket By Id")
    @DeleteMapping("/{id}")
    public Result deleteTicket(@RequestHeader String token ,@PathVariable long id) {
        return ticketServices.deleteTicket(id, token);
    }


    // this method is a put method and it is update assignee by ticket id
    @Operation(summary = "add Ticket Assignee")
    @PatchMapping("/{ticketId}/assignee/{assigneeId}")
    public Result addTicketAssignee(@RequestHeader String token, @PathVariable long ticketId, @PathVariable long assigneeId) {
        return ticketServices.addTicketAssignee(ticketId, assigneeId, token);
    }

    // this method is a delete method and it is remove assignee by ticket id
    @Operation(summary = "remove Ticket Assignee")
    @DeleteMapping("/{ticketId}/assignee/")
    public Result removeTickerAssignee(@RequestHeader String token ,@PathVariable long ticketId) {
        return ticketServices.removeTickerAssignee(ticketId, token);
    }

    // this method is a patch method and it is close ticket by ticket id
    @Operation(summary = "close Ticket")
    @PatchMapping("/{ticketId}")
    public Result closeTicket(@RequestHeader String token ,@PathVariable long ticketId) {
        return ticketServices.closeTicket(ticketId, token);
    }

    // this method is a get method and it is return all my ticket
    @Operation(summary = "get All My Ticket")
    @GetMapping("/myTickets")
    public ResultData<List<Ticket>> getAllMyTicket(@RequestHeader String token) {
        return ticketServices.getAllMyTicket(token);
    }

    @Operation(summary = "update Ticket Status")
    @PatchMapping("/{ticketId}/status/{statusId}")
    public Result updateTicketStatus(@RequestHeader String token ,@PathVariable long ticketId, @PathVariable long statusId) {
        return ticketServices.updateTicketStatus(ticketId, token, statusId);
    }

}
