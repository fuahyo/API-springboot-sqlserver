package NexDev.belajar_spring_restful_api_sqlServer.Service;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import NexDev.belajar_spring_restful_api_sqlServer.Entity.User;
import NexDev.belajar_spring_restful_api_sqlServer.Model.Request.LoginRequest;
import NexDev.belajar_spring_restful_api_sqlServer.Model.Response.LoginResponse;
import NexDev.belajar_spring_restful_api_sqlServer.Repository.UserRepository;
import NexDev.belajar_spring_restful_api_sqlServer.Security.BCrypt;
import jakarta.transaction.Transactional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    // @Autowired
    // private JwtTokenUtil jwtTokenUtil;

    public LoginResponse login(LoginRequest request) {
        validationService.validated(request);

        User userLogin = userRepository.findById(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or Password Salah"));

        if (BCrypt.checkpw(request.getPassword(), userLogin.getPassword())) {
            // String jwtToken = jwtTokenUtil.generateToken(request.getUsername());
            // userLogin.setToken(jwtToken);
            userLogin.setToken(UUID.randomUUID().toString());
            userLogin.setTokenExpiredAt(new Timestamp(System.currentTimeMillis() + (1000 * 60 * 30 * 1))); // 1000 mili
                                                                                                           // = 1 detik
                                                                                                           // 60 second
                                                                                                           // = 1 menit
                                                                                                           // 30 minute
                                                                                                           // = 0.5 jam
                                                                                                           // 1 hour

            userRepository.save(userLogin);

            return LoginResponse.builder()
                    .token(userLogin.getToken())
                    .tokenExpiredAt(userLogin.getTokenExpiredAt())
                    .username(userLogin.getUsername())
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or Password Salah");
        }
    }

    @Transactional
    public void logout(User user) {
        user.setToken(null);
        user.setTokenExpiredAt(null);
        userRepository.save(user);
    }

    // @Transactional(readOnly = true)
    // public User cekUserByToken(String token) {
    // if (token == null || !token.startsWith("Bearer ")) {
    // throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Token");
    // }
    // try {
    // String jwtToken = token.substring(7);
    // Claims claims = jwtTokenUtil.parseToken(jwtToken);
    // String username = claims.getSubject();
    // long expirationTimeMillis = claims.getExpiration().getTime();
    // log.info("Claims: {}", claims);
    // log.info("Username: {}", username);
    // log.info("Expiration Time (Millis): {}", expirationTimeMillis);
    // if (expirationTimeMillis < System.currentTimeMillis()) {
    // throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Expired Token");
    // }
    // User user = userRepository.findUserByUsername(username)
    // .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,
    // "Unauthorized"));

    // return user;
    // } catch (Exception e) {
    // throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.toString());
    // }

    // }
}
