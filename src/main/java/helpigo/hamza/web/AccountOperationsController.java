package helpigo.hamza.web;


import helpigo.hamza.Exceptions.BankAccountNotFoundException;
import helpigo.hamza.Exceptions.OperationNotFoundException;
import helpigo.hamza.dto.CreditRequestDTO;
import helpigo.hamza.dto.DebitRequestDTO;
import helpigo.hamza.dto.TransferRequestDTO;
import helpigo.hamza.entities.AccountOperations;
import helpigo.hamza.services.AccountOperationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account-operations")
@RequiredArgsConstructor
public class AccountOperationsController {

    private final AccountOperationsService accountOperationsService;

    @PostMapping("/debit")
    public ResponseEntity<?> debit(@RequestBody DebitRequestDTO request) throws BankAccountNotFoundException {
        return ResponseEntity.ok(accountOperationsService.debit(
                request.getAccountId(),
                request.getAmount(),
                request.getDescription()
        ));
    }
    @PostMapping("/credit")
    public ResponseEntity<?> credit(@RequestBody CreditRequestDTO requestDTO) throws BankAccountNotFoundException {
        return ResponseEntity.ok(accountOperationsService.credit(
                requestDTO.getAccountId(),
                requestDTO.getAmount(),
                requestDTO.getDescription()
        ));
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequestDTO transferRequestDTO) throws BankAccountNotFoundException {
        accountOperationsService.transfer(
                transferRequestDTO.getSourceAccountId(),
                transferRequestDTO.getDestinationAccountId(),
                transferRequestDTO.getAmount()
        );
        return ResponseEntity.ok("Transfer successful");
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountOperations> getOperationById(@PathVariable Long id) throws OperationNotFoundException {
        AccountOperations operation = accountOperationsService.getOperationById(id);
        return ResponseEntity.ok(operation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperationById(@PathVariable Long id) throws OperationNotFoundException {
        accountOperationsService.deleteOperationById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-account/{accountId}")
    public ResponseEntity<List<AccountOperations>> getOperationsByAccountId(@PathVariable String accountId) throws OperationNotFoundException {
        List<AccountOperations> operations = accountOperationsService.getOperationByAccountId(accountId);
        return ResponseEntity.ok(operations);
    }
}