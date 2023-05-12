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
public class CityEntity {
    @Id
    private UUID cityId;
    private String name;
}
