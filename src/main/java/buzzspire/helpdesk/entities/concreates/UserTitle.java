package buzzspire.helpdesk.entities.concreates;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "user_titles")
public class UserTitle {
    @GeneratedValue(generator = "increment")
    @Id
    private long id;
    private String title;
}
