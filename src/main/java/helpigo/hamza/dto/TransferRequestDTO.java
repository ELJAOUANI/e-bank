package helpigo.hamza.dto;

import lombok.Data;

@Data
public class TransferRequestDTO {
    private String sourceAccountId;
    private String destinationAccountId;
    private double amount;


}