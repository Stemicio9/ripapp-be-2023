package it.ripapp.ripapp.EntityUpdate;

import it.ripapp.ripapp.jooqgen.enums.Serverstatus;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ServerinfoEntity {
    @Id
    private Serverstatus status;
}
