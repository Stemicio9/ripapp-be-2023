package it.ripapp.ripapp.entityUpdate;
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
    private String status;
}
