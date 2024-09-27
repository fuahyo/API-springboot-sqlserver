package NexDev.belajar_spring_restful_api_sqlServer.Controller;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import NexDev.belajar_spring_restful_api_sqlServer.Entity.User;
import NexDev.belajar_spring_restful_api_sqlServer.Model.Request.RegisterUserRequest;
import NexDev.belajar_spring_restful_api_sqlServer.Model.Response.UserResponse;
import NexDev.belajar_spring_restful_api_sqlServer.Model.Response.WebResponse;
import NexDev.belajar_spring_restful_api_sqlServer.Service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/api/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> registerUser(@RequestBody RegisterUserRequest request) {
        userService.registerUser(request);
        return WebResponse.<String>builder().message("success").code("ok").result("ok").build();
    }

    @GetMapping(path = "/api/users/current", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<UserResponse> get(User user) {
        // public WebResponse<UserResponse> get(@RequestHeader("Authentication") String
        // token) {
        // UserResponse userResponse = userService.getUserById(user);
        // UserResponse userResponse = userService.getUserByToken(token);
        UserResponse userResponse = userService.getUserByToken(user);
        return WebResponse.<UserResponse>builder()
                .result(userResponse)
                .message("success")
                .code("00")
                .build();
    }
}