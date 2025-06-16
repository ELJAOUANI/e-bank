package helpigo.hamza.services;

import helpigo.hamza.entities.BankAccount;

import java.util.List;
import java.util.Optional;

public interface BankAccountService {

        BankAccount saveBankAccount(BankAccount bankAccount);
        List<BankAccount> getAllBankAccounts();
        Optional<BankAccount> getBankAccountById(String id);
        void deleteBankAccountById(String id);
}
