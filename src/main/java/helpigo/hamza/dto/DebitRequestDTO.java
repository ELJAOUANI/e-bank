package helpigo.hamza.dto;

import lombok.Data;

@Data
public class DebitRequestDTO {
    private String accountId;
    private double amount;
    private String description;
}