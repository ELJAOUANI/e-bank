package helpigo.hamza.web;


import helpigo.hamza.entities.Customer;

import helpigo.hamza.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public Customer createUser(@RequestBody Customer user) {

        return customerService.saveUser(user);
    }

    @GetMapping
    public List<Customer> getAllUsers() {
        return customerService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<Customer> getUserById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }
}