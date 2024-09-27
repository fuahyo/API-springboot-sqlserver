package NexDev.belajar_spring_restful_api_sqlServer.Model.Request;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {
    @Id
    @NotBlank
    @Size(max = 100, min = 3)
    private String username;

    @NotBlank
    @Size(max = 100, min = 3)
    private String name;

    @NotBlank
    @Size(max = 100)
    @Email
    private String email;

    private int age;

}
