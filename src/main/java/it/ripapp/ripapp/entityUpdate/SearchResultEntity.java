package it.ripapp.ripapp.entityUpdate;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class SearchResultEntity {

    private String result;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultid;

}
