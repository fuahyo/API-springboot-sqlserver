package NexDev.belajar_spring_restful_api_sqlServer.Model.Response;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {

    private String username;

    private String token;

    private Timestamp tokenExpiredAt;

}
