package buzzspire.helpdesk.api;

import buzzspire.helpdesk.business.concreates.TicketMessageManager;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dto.request.TicketMessage.TicketMessageRequest;
import buzzspire.helpdesk.entities.concreates.TicketMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Tag(name = "Ticket Massage APIs")
@RestController
@RequestMapping("/api/massages")
public class TicketMessageController {

    // this is a field injection
   private final TicketMessageManager ticketMassageManager;

    // this is a constructor injection
    public TicketMessageController(TicketMessageManager ticketMassageManager) {
        this.ticketMassageManager = ticketMassageManager;
    }

    // this method is a get method and it is return all ticket massage
    @Operation(summary = "Get all Ticket Massages")
    @GetMapping("/")
    public ResultData<List<TicketMessage>> getAll() {
        return ticketMassageManager.getAll();
    }

    // this method is a post method and it is add ticket massage
    @Operation(summary = "Add Ticket Massage")
    @PostMapping("/")
    public Result add(@RequestHeader String token,@RequestBody TicketMessageRequest TicketMessage) {
        return ticketMassageManager.add(TicketMessage, token);
    }

    // this method is a delete method and it is delete ticket massage by id
    @Operation(summary = "Delete Ticket Massage")
    @DeleteMapping("/{id}")
    public Result delete(@RequestHeader String token,@PathVariable long id) {
        return ticketMassageManager.delete(id, token);
    }

    // this method is a get method and it is return ticket massage by id
    @Operation(summary = "Get all Ticket Massages by massage id")
    @GetMapping("/{id}")
    public ResultData<TicketMessage> getById(@PathVariable long id) {
        return ticketMassageManager.getById(id);
    }

}
