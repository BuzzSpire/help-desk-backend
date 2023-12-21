package buzzspire.helpdesk;

import buzzspire.helpdesk.business.abstracts.UserServices;
import buzzspire.helpdesk.business.abstracts.UserTitleServices;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dataAccess.abstracts.UserDAO;
import buzzspire.helpdesk.entities.concreates.RoleEnum;
import buzzspire.helpdesk.entities.concreates.User;
import buzzspire.helpdesk.entities.concreates.UserTitle;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelpdeskApplication {
	private final UserServices userServices;
	private final UserTitleServices userTitleServices;
	private final UserDAO userDAO;

	private final String ADMIN_EMAIL = "admin@test.com";
	public HelpdeskApplication(UserServices userServices, UserTitleServices userTitleServices, UserDAO userDAO) {
		this.userServices = userServices;
		this.userTitleServices = userTitleServices;
		this.userDAO = userDAO;
	}

	@PostConstruct
	public void init() {
		UserTitle userTitle =  userTitleServices.getByTitle(RoleEnum.ADMIN.toString());
		ResultData<User> admin = userServices.getByEmail(ADMIN_EMAIL);

		if(userTitle == null && admin.getData() == null) {
			userTitleServices.add(RoleEnum.ADMIN.toString());
			User defaultAdmin = User.builder()
					.name("Admin")
					.surname("Admin")
					.email(ADMIN_EMAIL)
					.password("123456")
					.role(RoleEnum.ADMIN)
					.title(userTitleServices.getByTitle(RoleEnum.ADMIN.toString())).build();
			userDAO.save(defaultAdmin);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

}
