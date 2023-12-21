package buzzspire.helpdesk.business.concreates;

import buzzspire.helpdesk.business.abstracts.TicketPriorityServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dataAccess.abstracts.TicketPriorityDAO;
import buzzspire.helpdesk.entities.concreates.RoleEnum;
import buzzspire.helpdesk.entities.concreates.TicketPriority;
import buzzspire.helpdesk.security.JwtTokenProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketPriorityManager implements TicketPriorityServices {

    // this filed injection
    private TicketPriorityDAO ticketPriorityDAO;
    private JwtTokenProvider jwtTokenProvider;

    // constructor injection
    public TicketPriorityManager(TicketPriorityDAO ticketPriorityDAO) {
        this.ticketPriorityDAO = ticketPriorityDAO;
        this.jwtTokenProvider = new JwtTokenProvider();
    }

    // this method is used for get all ticket
    @Override
    public ResultData<List<TicketPriority>> getAll(String token) {
        if (!jwtTokenProvider.validateToken(token)){
            return new ResultData<>(null, "Token is not valid", false);
        }

        String role = jwtTokenProvider.getRoleFromToken(token);
        if(role.equals(RoleEnum.ADMIN.toString()) || role.equals(RoleEnum.SUPPORT.toString()) || role.equals(RoleEnum.USER.toString())){
            return new ResultData<>(this.ticketPriorityDAO.findAll(), "Ticket priority list", true);
        }else {
            return new ResultData<>(null, "You are not authorized", false);
        }

    }

    // this method is used for add ticket
    @Override
    public Result add(String token,String priority) {
        if (!jwtTokenProvider.validateToken(token)){
            return new Result(false, "Token is not valid");
        }

        String role = jwtTokenProvider.getRoleFromToken(token);
        if(!role.equals(RoleEnum.ADMIN.toString())){
            return new Result(false, "You are not authorized");
        }

        try{
            this.ticketPriorityDAO.save(TicketPriority.builder().priority(priority).build());
        } catch (Exception e) {
            return new Result(false, "Ticket priority not added");
        }

        return new Result(true, "Ticket priority added");
    }

    // this method is used for delete ticket
    @Override
    public Result delete(String token,long id) {
        if (!jwtTokenProvider.validateToken(token)){
            return new Result(false, "Token is not valid");
        }

        String role = jwtTokenProvider.getRoleFromToken(token);
        if(!role.equals(RoleEnum.ADMIN.toString())){
            return new Result(false, "You are not authorized");
        }

        try{
            this.ticketPriorityDAO.deleteById(id);
        } catch (Exception e) {
            return new Result(false, "Ticket priority not deleted");
        }
        return new Result(true, "Ticket priority deleted");
    }
}
