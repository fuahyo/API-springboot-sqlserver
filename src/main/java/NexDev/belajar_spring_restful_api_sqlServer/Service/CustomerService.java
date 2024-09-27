package NexDev.belajar_spring_restful_api_sqlServer.Service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import NexDev.belajar_spring_restful_api_sqlServer.Entity.Customer;
import NexDev.belajar_spring_restful_api_sqlServer.Entity.User;
import NexDev.belajar_spring_restful_api_sqlServer.Model.Request.CustomerRequest;
import NexDev.belajar_spring_restful_api_sqlServer.Model.Response.CustomerResponse;
import NexDev.belajar_spring_restful_api_sqlServer.Model.Response.LoginResponse;
import NexDev.belajar_spring_restful_api_sqlServer.Repository.CustomerRepository;
import jakarta.transaction.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public CustomerResponse createCustomer(User user, CustomerRequest customerRequest) {
        validationService.validated(customerRequest);

        if (user.getToken() == null
                || user.getTokenExpiredAt().compareTo(new Timestamp(System.currentTimeMillis())) < 0) {
            // Handle invalid or expired token
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "TOKEN INVALID");
        }
        Customer customerEntity = new Customer();
        customerEntity.setName(customerRequest.getName());
        customerEntity.setUsername(customerRequest.getUsername());
        customerEntity.setEmail(customerRequest.getEmail());
        customerEntity.setAge(customerRequest.getAge());
        customerRepo.save(customerEntity);

        return CustomerResponse.builder()
                .name(customerRequest.getName())
                .username(customerRequest.getUsername())
                .email(customerRequest.getEmail())
                .age(customerRequest.getAge())
                .build();

    }

}
