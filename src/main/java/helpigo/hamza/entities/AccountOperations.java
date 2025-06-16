package helpigo.hamza.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import helpigo.hamza.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class AccountOperations {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Date operationDate ;

    private double amount ;
    @Enumerated(EnumType.STRING)
    private OperationType operationType ;


    @ManyToOne
    @JsonIgnore
    private BankAccount bankAccount;
    private String description;

}
