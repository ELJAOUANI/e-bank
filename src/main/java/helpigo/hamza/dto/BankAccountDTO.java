package helpigo.hamza.dto;

import helpigo.hamza.entities.Customer;
import helpigo.hamza.enums.AccountStatus;
import lombok.Data;

import java.util.Date;

@Data
public class BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private String accountType;
    private AccountStatus accountStatus;
    private Long customerId; // Assuming you want to include the customer ID
    private Double overDraft;
    private Double interestRate;
}