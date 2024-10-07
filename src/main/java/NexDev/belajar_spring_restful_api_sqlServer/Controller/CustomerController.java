package NexDev.belajar_spring_restful_api_sqlServer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import NexDev.belajar_spring_restful_api_sqlServer.Entity.User;
import NexDev.belajar_spring_restful_api_sqlServer.Model.Request.CustomerRequest;
import NexDev.belajar_spring_restful_api_sqlServer.Model.Request.RegisterUserRequest;
import NexDev.belajar_spring_restful_api_sqlServer.Model.Request.UpdateCustomerRequest;
import NexDev.belajar_spring_restful_api_sqlServer.Model.Response.CustomerResponse;
import NexDev.belajar_spring_restful_api_sqlServer.Model.Response.WebResponse;
import NexDev.belajar_spring_restful_api_sqlServer.Service.CustomerService;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService cutomerService;

    @PostMapping(path = "/api/customers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<CustomerResponse> createCustomer(User user, @RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = cutomerService.createCustomer(user, customerRequest);
        return WebResponse.<CustomerResponse>builder().message("success")
                .code("00")
                .result(customerResponse)
                .build();
    }

    @GetMapping(path = "/api/customers/{CustomerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<CustomerResponse> getCustomer(User user, @PathVariable("CustomerId") String CustomerId) {
        CustomerResponse customerResponse = cutomerService.getCustomer(user, CustomerId);
        return WebResponse.<CustomerResponse>builder().message("success")
                .code("00")
                .result(customerResponse)
                .build();
    }

    @PutMapping(path = "/api/customers/{CustomerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<CustomerResponse> updateCustomer(User user,
            @RequestBody UpdateCustomerRequest updateCustomerRequest,
            @PathVariable("CustomerId") String CustomerId) {

        updateCustomerRequest.setId(CustomerId);
        CustomerResponse customerResponse = cutomerService.updateCustomerService(user, updateCustomerRequest);
        return WebResponse.<CustomerResponse>builder().message("data succesfully updated ").code("00")
                .result(customerResponse).build();
    }

    @DeleteMapping(path = "/api/customers/{CustomerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> deleteCustomer(User user, @PathVariable("CustomerId") String CustomerId) {
        cutomerService.deleteCustomer(user, CustomerId);
        return WebResponse.<String>builder().message("ok").code("00").result("ok").build();
    }
}
