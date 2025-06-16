package helpigo.hamza.mappers;

import helpigo.hamza.dto.BankAccountDTO;
import helpigo.hamza.entities.BankAccount;
import helpigo.hamza.entities.CurrentAccount;
import helpigo.hamza.entities.Customer;
import helpigo.hamza.entities.SavingAccount;

public class BankAccountMapper {

    public static BankAccount fromDto(BankAccountDTO dto, Customer customer) {
        if ("CA".equals(dto.getAccountType())) {
            CurrentAccount ca = new CurrentAccount();
            ca.setOverDraft(dto.getOverDraft());
            copyCommonFields(dto, ca, customer);
            return ca;
        } else if ("SA".equals(dto.getAccountType())) {
            SavingAccount sa = new SavingAccount();
            sa.setInterestRate(dto.getInterestRate());
            copyCommonFields(dto, sa, customer);
            return sa;
        }
        return null;
    }

    private static void copyCommonFields(BankAccountDTO dto, BankAccount account, Customer customer) {
        account.setId(dto.getId());
        account.setBalance(dto.getBalance());
        account.setCreatedAt(dto.getCreatedAt());
        account.setAccountStatus(dto.getAccountStatus());
        account.setCustomer(customer);
    }

    public static BankAccountDTO toDto(BankAccount account) {
        BankAccountDTO dto = new BankAccountDTO();
        dto.setId(account.getId());
        dto.setBalance(account.getBalance());
        dto.setCreatedAt(account.getCreatedAt());
        dto.setAccountStatus(account.getAccountStatus());
        dto.setCustomerId(account.getCustomer().getId());

        if (account instanceof CurrentAccount ca) {
            dto.setAccountType("CA");
            dto.setOverDraft(ca.getOverDraft());
        } else if (account instanceof SavingAccount sa) {
            dto.setAccountType("SA");
            dto.setInterestRate(sa.getInterestRate());
        }
        return dto;
    }
}
