package NexDev.belajar_spring_restful_api_sqlServer.Service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import NexDev.belajar_spring_restful_api_sqlServer.Entity.Customer;
import NexDev.belajar_spring_restful_api_sqlServer.Entity.User;
import NexDev.belajar_spring_restful_api_sqlServer.Model.Request.CustomerRequest;
import NexDev.belajar_spring_restful_api_sqlServer.Model.Request.UpdateCustomerRequest;
import NexDev.belajar_spring_restful_api_sqlServer.Model.Response.CustomerResponse;
import NexDev.belajar_spring_restful_api_sqlServer.Model.Response.LoginResponse;
import NexDev.belajar_spring_restful_api_sqlServer.Repository.CustomerRepository;

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
        customerEntity.setEmail(customerRequest.getEmail());
        customerEntity.setAge(customerRequest.getAge());
        customerEntity.setUser(user);

        customerRepo.save(customerEntity);

        return toCustomerResponseReturn(customerEntity);

    }

    private CustomerResponse toCustomerResponseReturn(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .age(customer.getAge())
                .build();
    }

    @Transactional(readOnly = true)
    public CustomerResponse getCustomer(User user, String id) {
        Customer customerEntity = customerRepo.findFirstByUserAndId(user, id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        return toCustomerResponseReturn(customerEntity);

    }

    @Transactional
    public CustomerResponse updateCustomerService(User user, UpdateCustomerRequest updateCustomerRequest) {
        validationService.validated(updateCustomerRequest);

        Customer customerEntity = customerRepo.findFirstByUserAndId(user, updateCustomerRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found"));

        customerEntity.setAge(updateCustomerRequest.getAge());
        customerEntity.setName(updateCustomerRequest.getName());
        customerEntity.setEmail(updateCustomerRequest.getEmail());
        customerRepo.save(customerEntity);

        return toCustomerResponseReturn(customerEntity);
    }

    @Transactional
    public CustomerResponse deleteCustomer(User user, String id) {
        Customer customerEntity = customerRepo.findFirstByUserAndId(user, id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        // return toCustomerResponseReturn(customerEntity);

        customerRepo.deleteById(id);

        return toCustomerResponseReturn(customerEntity);

    }
}
