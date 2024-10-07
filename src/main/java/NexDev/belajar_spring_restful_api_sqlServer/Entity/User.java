package NexDev.belajar_spring_restful_api_sqlServer.Entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    private String username;

    private String name;
    private String email;
    private String Network_id;
    private String password;

    @Column(name = "remember_token")
    private String token;

    private Timestamp created_at;
    private Timestamp updated_at;

    @Column(name = "token_expired_at")
    private Timestamp tokenExpiredAt;

    @OneToMany(mappedBy = "user")
    private List<Customer> customer;
}
