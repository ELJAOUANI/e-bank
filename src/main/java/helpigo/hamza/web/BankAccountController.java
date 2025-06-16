package helpigo.hamza.web;

import helpigo.hamza.Exceptions.CustomerNotFoundException;
import helpigo.hamza.dto.BankAccountDTO;
import helpigo.hamza.entities.BankAccount;
import helpigo.hamza.entities.Customer;
import helpigo.hamza.entities.SavingAccount;
import helpigo.hamza.mappers.BankAccountMapper;
import helpigo.hamza.services.BankAccountService;
import helpigo.hamza.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<BankAccountDTO> createBankAccount(@RequestBody BankAccountDTO bankAccountDTO) throws CustomerNotFoundException {
        Customer customer = customerService.getCustomerById(bankAccountDTO.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        BankAccount bankAccount = BankAccountMapper.fromDto(bankAccountDTO, customer);
        BankAccount savedBankAccount = bankAccountService.saveBankAccount(bankAccount);
        return ResponseEntity.ok(BankAccountMapper.toDto(savedBankAccount));
    }


    @GetMapping
    public ResponseEntity<List<BankAccountDTO>> getAllBankAccounts() {
        List<BankAccount> bankAccounts = bankAccountService.getAllBankAccounts();
        List<BankAccountDTO> bankAccountDTOs = bankAccounts.stream()
                .map(BankAccountMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(bankAccountDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountDTO> getBankAccountById(@PathVariable String id) {
        BankAccount bankAccount = bankAccountService.getBankAccountById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bank account not found"));
        return ResponseEntity.ok(BankAccountMapper.toDto(bankAccount));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankAccountById(@PathVariable String id) {
        bankAccountService.deleteBankAccountById(id);
        return ResponseEntity.noContent().build();
    }
}