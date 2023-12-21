package buzzspire.helpdesk.business.concreates;

import buzzspire.helpdesk.business.abstracts.TicketStatusServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dataAccess.abstracts.TicketStatusDAO;
import buzzspire.helpdesk.entities.concreates.RoleEnum;
import buzzspire.helpdesk.entities.concreates.TicketStatus;
import buzzspire.helpdesk.security.JwtTokenProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketStatusManager implements TicketStatusServices {
    // filed injection
    private final TicketStatusDAO ticketStatusDAO;
    private final JwtTokenProvider jwtTokenProvider;

    // constructor injection
    public TicketStatusManager(TicketStatusDAO ticketStatusDAO, JwtTokenProvider jwtTokenProvider) {
        this.ticketStatusDAO = ticketStatusDAO;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    // this method is used to get all ticket status
    @Override
    public ResultData<List<TicketStatus>> getAll(String token) {
        if(!jwtTokenProvider.validateToken(token)){
            return new ResultData<>(null, "Token is not valid", false);
        }

        String role = jwtTokenProvider.getRoleFromToken(token);
        if(role.equals(RoleEnum.ADMIN.name()) || role.equals(RoleEnum.SUPPORT.name()) || role.equals(RoleEnum.USER.name())){
            return new ResultData<>(this.ticketStatusDAO.findAll(), "ticket status list", true);
        }
        return new ResultData<>(null, "You are not authorized", false);
    }

    // this method is used to add ticket status
    @Override
    public Result add(String token,String status) {

        if(!jwtTokenProvider.validateToken(token)){
            return new Result(false, "Token is not valid");
        }

        String role = jwtTokenProvider.getRoleFromToken(token);
        if (role.equals(RoleEnum.ADMIN.toString())) {
        try {
                this.ticketStatusDAO.save(TicketStatus.builder().status(status).build());
            } catch (Exception e) {
                return new Result(false, "Ticket Status Not Added");
            }
            return new Result(true, "Ticket Status Added");
        } else {
            return new Result(false, "You are not authorized");
        }

    }

    // this method is used to delete ticket status
    @Override
    public Result delete(String token,long id) {
        if(!jwtTokenProvider.validateToken(token)){
            return new Result(false, "Token is not valid");
        }

        String role = jwtTokenProvider.getRoleFromToken(token);
        if(role.equals(RoleEnum.ADMIN.toString())){
        try {
                this.ticketStatusDAO.deleteById(id);
            } catch (Exception e) {
                return new Result(false, "Ticket Status Not Deleted");
            }
            return new Result(true, "Ticket Status Deleted");
        }  else {
            return new Result(false, "You are not authorized");
        }

    }

}
