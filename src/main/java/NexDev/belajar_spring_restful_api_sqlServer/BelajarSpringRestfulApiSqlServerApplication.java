package NexDev.belajar_spring_restful_api_sqlServer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import NexDev.belajar_spring_restful_api_sqlServer.Entity.Customer;
import NexDev.belajar_spring_restful_api_sqlServer.Repository.CustomerRepository;

@SpringBootApplication
@RestController
@RequestMapping("/api/customers")
public class BelajarSpringRestfulApiSqlServerApplication {

	@Autowired
	private CustomerRepository customerRepo;

	@GetMapping
	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	public static void main(String[] args) {
		SpringApplication.run(BelajarSpringRestfulApiSqlServerApplication.class, args);
	}

	// @GetMapping("/hello")
	// public String hello(@RequestParam(value = "name", defaultValue = "World")
	// String name) {
	// return String.format("Hello %s!", name);
	// }

	// @Override
	// public void run(String... args) throws Exception {
	// List<Customer> customerData = customerRepo.findAll();
	// customerData.forEach(System.out::println);
	// }
}
