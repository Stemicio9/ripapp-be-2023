package it.ripapp.ripapp.entityUpdate;

import it.ripapp.ripapp.utilities.SearchSorting;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class FilterEntity {
    @Id
    private UUID id;
    //TODO fare classe cities e aggiornare
    //@OneToMany
    //private List<CityEntity> cities;
    private String sortingVal;
    private Integer offsetVal;
    private Integer limitVal;
}
