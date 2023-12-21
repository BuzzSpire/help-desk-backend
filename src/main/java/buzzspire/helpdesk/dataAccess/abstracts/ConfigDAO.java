package buzzspire.helpdesk.dataAccess.abstracts;

import buzzspire.helpdesk.entities.concreates.Config;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigDAO extends JpaRepository<Config, Long> {
}
