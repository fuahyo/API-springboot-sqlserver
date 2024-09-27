package NexDev.belajar_spring_restful_api_sqlServer.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebResponse<T> {
    private String code;
    private String message;
    private String messageError;
    private T result;
}
