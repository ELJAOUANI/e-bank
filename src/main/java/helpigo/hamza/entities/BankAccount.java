package helpigo.hamza.entities;

import helpigo.hamza.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE",length = 4)
@Data  @AllArgsConstructor @NoArgsConstructor
public abstract class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Use UUID generation strategy
    private String Id ;
    private  double balance ;
    private Date CreatedAt;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @ManyToOne
    private Customer customer;


    @OneToMany(mappedBy = "bankAccount" , fetch = FetchType.LAZY)
   private  List<AccountOperations> accountOperations;


}
