package NexDev.belajar_spring_restful_api_sqlServer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import NexDev.belajar_spring_restful_api_sqlServer.Entity.User;
import NexDev.belajar_spring_restful_api_sqlServer.Model.Request.CustomerRequest;
import NexDev.belajar_spring_restful_api_sqlServer.Model.Request.RegisterUserRequest;
import NexDev.belajar_spring_restful_api_sqlServer.Model.Response.WebResponse;
import NexDev.belajar_spring_restful_api_sqlServer.Service.CustomerService;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService cutomerService;

    @PostMapping(path = "/api/customers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> createCustomer(User user, @RequestBody CustomerRequest customerRequest) {
        cutomerService.createCustomer(user, customerRequest);
        return WebResponse.<String>builder().message("success").code("ok").result("ok").build();
    }
}
