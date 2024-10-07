package NexDev.belajar_spring_restful_api_sqlServer.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "customer")
public class Customer {

    @Id
    private String id;

    private String name;

    private String email;

    private int age;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;
    // ALTER TABLE users
    // DROP CONSTRAINT <existing_primary_key_constraint_name>; --check on username
    // field in "indexes/keys" option

    // ALTER TABLE users
    // ADD CONSTRAINT UQ_users_username UNIQUE (username);

    // ALTER TABLE customer
    // WITH NOCHECK
    // ADD CONSTRAINT FK_customer_username FOREIGN KEY (username) REFERENCES
    // users(username)

}
