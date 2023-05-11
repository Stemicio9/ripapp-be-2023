package it.ripapp.ripapp.entityUpdate;

import it.ripapp.ripapp.entities.ContactEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PhoneBookEntity {
    @Id
    @OneToOne
    private AccountEntity accountid;

    @OneToMany
    Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<ContactEntity> contacts;
    private Integer offset;
    private Integer total;
    private Boolean hasnextchunk;
}
