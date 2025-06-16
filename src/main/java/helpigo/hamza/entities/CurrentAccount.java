package helpigo.hamza.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@DiscriminatorValue("CA")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CurrentAccount extends BankAccount{

private double overDraft;
}
