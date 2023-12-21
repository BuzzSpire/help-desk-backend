package buzzspire.helpdesk.business.concreates;

import buzzspire.helpdesk.business.abstracts.TicketMessageServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dataAccess.abstracts.TicketDAO;
import buzzspire.helpdesk.dataAccess.abstracts.TicketMessageDAO;
import buzzspire.helpdesk.dto.request.TicketMessage.TicketMessageRequest;
import buzzspire.helpdesk.entities.concreates.RoleEnum;
import buzzspire.helpdesk.entities.concreates.Ticket;
import buzzspire.helpdesk.entities.concreates.TicketMessage;
import buzzspire.helpdesk.entities.concreates.User;
import buzzspire.helpdesk.security.JwtTokenProvider;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketMessageManager implements TicketMessageServices {

    // this field injection
    private final TicketMessageDAO ticketMessageDAO;
    private final JwtTokenProvider jwtTokenProvider;
    private final TicketDAO ticketDAO;
    // constructor injection
    public TicketMessageManager(TicketMessageDAO ticketMessageDAO, JwtTokenProvider jwtTokenProvider, TicketDAO ticketDAO){
        this.ticketMessageDAO = ticketMessageDAO;
        this.jwtTokenProvider = jwtTokenProvider;
        this.ticketDAO = ticketDAO;
    }

    // this method is used to get all ticket messages
    @Override
    public ResultData<List<TicketMessage>> getAll() {
        return new ResultData<>(ticketMessageDAO.findAll(), "Ticket Messages listed", true);
    }

    // this method is used to add a ticket message
    @Override
    public Result add(TicketMessageRequest TicketMessage, String token) {
        if (!jwtTokenProvider.validateToken(token)){
            return new Result(false, "Token is not valid");
        }

        long senderId = jwtTokenProvider.getIdFromToken(token);
        String role = jwtTokenProvider.getRoleFromToken(token);
        Ticket ticket = ticketDAO.findById(TicketMessage.getTicketId()).orElse(null);
        Date date = new Date();

        if (ticket == null){
            return new Result(false, "Ticket could not be found");
        }
        boolean isAuthorized = senderId == ticket.getUser().getId()
                || role.equals(RoleEnum.ADMIN.toString())
                || role.equals(RoleEnum.SUPPORT.toString());

        if (!isAuthorized){
            return new Result(false, "You are not authorized to add ticket message");
        }

        buzzspire.helpdesk.entities.concreates.TicketMessage ticketMessage = buzzspire.helpdesk.entities.concreates.TicketMessage.builder()
                .message(TicketMessage.getMessage())
                .date(date)
                .sender(User.builder().id(senderId).build())
                .ticket(Ticket.builder().id(TicketMessage.getTicketId()).build())
                .build();

        try {
            ticketMessageDAO.save(ticketMessage);
        } catch (Exception e) {
            return new Result(false, "Ticket Message could not be added");
        }
        return new Result(true, "Ticket Message added");
    }

    // this method is used to delete a ticket message
    @Override
    public Result delete(long id, String token) {
        long userId = jwtTokenProvider.getIdFromToken(token);
        String role = jwtTokenProvider.getRoleFromToken(token);
        TicketMessage ticketMessage = ticketMessageDAO.findById(id).orElse(null);
        if (ticketMessage == null){
            return new Result(false, "Ticket Message could not be found");
        }

        boolean isAuthorized = userId == ticketMessage.getSender().getId()
                || role.equals(RoleEnum.ADMIN.toString())
                || role.equals(RoleEnum.SUPPORT.toString());

        if (!isAuthorized){
            return new Result(false, "You are not authorized to delete ticket message");
        }

        try {
            ticketMessageDAO.deleteById(id);
        } catch (Exception e) {
            return new Result(false, "Ticket Message could not be deleted");
        }
        return new Result(true, "Ticket Message deleted");
    }

    // this method is used to get a ticket message by id
    @Override
    public ResultData<TicketMessage> getById(long id) {
        return new ResultData<>(ticketMessageDAO.findById(id).orElse(null), "Ticket Message listed", true);}
}
