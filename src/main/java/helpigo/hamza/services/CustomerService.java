package helpigo.hamza.services;

import helpigo.hamza.entities.Customer;
import helpigo.hamza.repository.CustomerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
public interface CustomerService {
    Customer saveUser(Customer user);
    List<Customer> getAllUsers();
    Optional<Customer> getCustomerById(Long id);
}