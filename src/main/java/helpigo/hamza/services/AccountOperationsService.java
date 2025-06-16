package helpigo.hamza.services;

import helpigo.hamza.Exceptions.BankAccountNotFoundException;
import helpigo.hamza.Exceptions.OperationNotFoundException;
import helpigo.hamza.entities.AccountOperations;

import java.util.List;

public interface AccountOperationsService {

    AccountOperations debit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    AccountOperations credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    void transfer(String sourceAccountId, String destinationAccountId, double amount) throws BankAccountNotFoundException;
    AccountOperations getOperationById(Long id) throws OperationNotFoundException;
    void deleteOperationById(Long id) throws OperationNotFoundException;
    List<AccountOperations> getOperationByAccountId(String accountId) throws OperationNotFoundException;
}
