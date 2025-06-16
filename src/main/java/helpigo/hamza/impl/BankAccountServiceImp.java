package helpigo.hamza.impl;

import helpigo.hamza.entities.BankAccount;
import helpigo.hamza.repository.BankAccountRepository;
import helpigo.hamza.services.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class BankAccountServiceImp implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    @Override
    public BankAccount saveBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }
    @Override
    public Optional<BankAccount> getBankAccountById(String id) {
        return bankAccountRepository.findById(id);
    }

    @Override
    public  void deleteBankAccountById(String id) {
        bankAccountRepository.deleteById(id);
    }

}
