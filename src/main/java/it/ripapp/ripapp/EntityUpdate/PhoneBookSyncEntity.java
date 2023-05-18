package it.ripapp.ripapp.EntityUpdate;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class PhoneBookSyncEntity {
    @Id
    private Long id;
    //TODO fare entità contatti e poi aggiornare
    //@OneToMany
   // private List<ContactEntity> contacts;
    private Integer offset;
    private Integer total;
    private Boolean hasnextchunk;
}
