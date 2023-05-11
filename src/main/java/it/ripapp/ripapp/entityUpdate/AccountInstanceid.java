package it.ripapp.ripapp.entityUpdate;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountInstanceid {
    @Id
    String instanceId;

    @OneToOne
    it.ripapp.ripapp.entityUpdate.AccountEntity account;

}
