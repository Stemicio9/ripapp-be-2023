package it.ripapp.ripapp.entityUpdate;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ContactEntity {
    @Id
    private UUID contactid;
    private String num;
    private String name;
    private String phonehash;
    private String prefix;

}
