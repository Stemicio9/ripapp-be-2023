package it.ripapp.ripapp.entityUpdate;

import lombok.*;
import org.hibernate.annotations.Cascade;

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
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<ContactEntity> contacts;
    private Integer offset;
    private Integer total;
    private Boolean hasnextchunk;
}
