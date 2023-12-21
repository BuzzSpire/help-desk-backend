package buzzspire.helpdesk.entities.concreates;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @GeneratedValue(generator = "increment")
    @Id
    private long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private RoleEnum role;
    @ManyToOne
    private UserTitle title;
}
