package helpigo.hamza.impl;

import helpigo.hamza.Exceptions.BankAccountNotFoundException;
import helpigo.hamza.Exceptions.CustomerNotFoundException;
import helpigo.hamza.Exceptions.OperationNotFoundException;
import helpigo.hamza.entities.AccountOperations;
import helpigo.hamza.entities.BankAccount;
import helpigo.hamza.enums.OperationType;
import helpigo.hamza.repository.AccountOperationRepository;
import helpigo.hamza.repository.BankAccountRepository;
import helpigo.hamza.services.AccountOperationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountOperationsServiceImpl implements AccountOperationsService {

    private final AccountOperationRepository accountOperationRepository;
    private final BankAccountRepository bankAccountRepository;

    @Override
    public AccountOperations debit(String accountId, double amount, String description) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account not found"));
        if (bankAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        AccountOperations operation = new AccountOperations(null, new Date(), amount, OperationType.DEBIT, bankAccount, description);
        bankAccountRepository.save(bankAccount);
        return accountOperationRepository.save(operation);
    }

    @Override
    public AccountOperations credit(String accountId, double amount, String description) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account not found"));
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        AccountOperations operation = new AccountOperations(null, new Date(), amount, OperationType.CREDIT, bankAccount, description);
        bankAccountRepository.save(bankAccount);
        return accountOperationRepository.save(operation);
    }

    @Override
    public void transfer(String sourceAccountId, String destinationAccountId, double amount) throws BankAccountNotFoundException {
        debit(sourceAccountId, amount, "Transfer to account " + destinationAccountId);
        credit(destinationAccountId, amount, "Transfer from account " + sourceAccountId);
    }

    @Override
    public AccountOperations getOperationById(Long id) throws OperationNotFoundException {
        return accountOperationRepository.findById(id)
                .orElseThrow(() -> new OperationNotFoundException("Operation not found with id: " + id));
    }

    @Override
    public void deleteOperationById(Long id) throws OperationNotFoundException {
        if (!accountOperationRepository.existsById(id)) {
            throw new OperationNotFoundException("Operation not found with id: " + id);
        }
        accountOperationRepository.deleteById(id);
    }
    @Override
    public List<AccountOperations> getOperationByAccountId(String accountId) throws OperationNotFoundException {
        List<AccountOperations> operations = accountOperationRepository.findByBankAccount_Id(accountId);
        if (operations.isEmpty()) {
            throw new OperationNotFoundException("No operations found for account id: " + accountId);
        }
        return operations;
    }


}
