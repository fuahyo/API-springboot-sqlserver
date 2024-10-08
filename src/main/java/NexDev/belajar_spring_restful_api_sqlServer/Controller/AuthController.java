package NexDev.belajar_spring_restful_api_sqlServer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import NexDev.belajar_spring_restful_api_sqlServer.Entity.User;
import NexDev.belajar_spring_restful_api_sqlServer.Model.Request.LoginRequest;
import NexDev.belajar_spring_restful_api_sqlServer.Model.Response.LoginResponse;
import NexDev.belajar_spring_restful_api_sqlServer.Model.Response.WebResponse;
import NexDev.belajar_spring_restful_api_sqlServer.Service.AuthService;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/api/auth/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse loginResponse = authService.login(request);
        return WebResponse.<LoginResponse>builder()
                .result(loginResponse)
                .code("00")
                .message("Login Success")
                .build();
    }

    @DeleteMapping(path = "/api/auth/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> logout(User user) {
        authService.logout(user);
        return WebResponse.<String>builder()
                .result("Logout Success")
                .code("00")
                .message("Success")
                .build();
    }

    // @DeleteMapping(path = "/api/auth/logout", produces =
    // MediaType.APPLICATION_JSON_VALUE)
    // public WebResponse<String> logout(User user) {
    // authService.logout(user);
    // return WebResponse.<String>builder()
    // .result("Logout Success")
    // .code("00")
    // .message("Success")
    // .build();
    // }

}
