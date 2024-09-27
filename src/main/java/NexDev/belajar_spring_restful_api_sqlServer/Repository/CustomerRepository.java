package NexDev.belajar_spring_restful_api_sqlServer.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import NexDev.belajar_spring_restful_api_sqlServer.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findUserByUsername(String username);
}
