package it.ripapp.ripapp.EntityUpdate;

import it.ripapp.ripapp.entities.ContactEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class PhoneBookSyncEntity {
    @Id
    //TODO fare entità contatti e poi aggiornare
    //@OneToMany
   // private List<ContactEntity> contacts;
    private Integer offset;
    private Integer total;
    private Boolean hasnextchunk;
}
