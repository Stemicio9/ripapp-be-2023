package it.ripapp.ripapp.EntityUpdate;

import it.ripapp.ripapp.EntityUpdate.AccountEntity;
import it.ripapp.ripapp.EntityUpdate.ContactEntity;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PhoneBookEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    private AccountEntity accountid;

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<ContactEntity> contacts;
    private Integer offset;
    private Integer total;
    private Boolean hasnextchunk;
}
