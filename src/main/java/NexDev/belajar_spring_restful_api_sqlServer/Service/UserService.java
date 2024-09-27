package NexDev.belajar_spring_restful_api_sqlServer.Service;

// import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import NexDev.belajar_spring_restful_api_sqlServer.Entity.User;
import NexDev.belajar_spring_restful_api_sqlServer.Model.Request.RegisterUserRequest;
import NexDev.belajar_spring_restful_api_sqlServer.Model.Response.UserResponse;
import NexDev.belajar_spring_restful_api_sqlServer.Repository.UserRepository;
import NexDev.belajar_spring_restful_api_sqlServer.Security.BCrypt;
import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public void registerUser(RegisterUserRequest registerUserRequest) {
        // log.info("REQUEST SERVICE {}", registerUserRequest);
        validationService.validated(registerUserRequest);

        if (userRepo.existsById(registerUserRequest.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username Already Registered");
        }

        User user = new User();

        user.setUsername(registerUserRequest.getUsername());
        user.setPassword(BCrypt.hashpw(registerUserRequest.getPassword(), BCrypt.gensalt()));
        user.setName(registerUserRequest.getName());
        user.setEmail(registerUserRequest.getEmail());
        user.setNetwork_id(registerUserRequest.getNetwork_id());
        user.setCreated_at(new Timestamp(System.currentTimeMillis()));

        userRepo.save(user);
    }

    @Transactional()
    // public UserResponse getUserByToken(String token) {
    public UserResponse getUserByToken(User user) {
        // User user = authService.cekUserByToken(token);
        return UserResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .build();
    }

}
