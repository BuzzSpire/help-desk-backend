package buzzspire.helpdesk.dataAccess.abstracts;

import buzzspire.helpdesk.entities.concreates.UserTitle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTitleDAO extends JpaRepository<UserTitle, Long> {
    UserTitle getByTitle(String title);
}
