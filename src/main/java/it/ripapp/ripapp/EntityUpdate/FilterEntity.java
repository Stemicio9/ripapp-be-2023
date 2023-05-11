package it.ripapp.ripapp.EntityUpdate;

import it.ripapp.ripapp.entities.CityEntity;
import it.ripapp.ripapp.utilities.SearchSorting;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class FilterEntity {
    @Id
    //TODO fare classe cities e aggiornare
    //@OneToMany
    //private List<CityEntity> cities;
    private SearchSorting sorting;
    private int offset;
    private int limit;
}
