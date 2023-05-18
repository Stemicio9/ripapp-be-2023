package it.ripapp.ripapp.EntityUpdate;

import it.ripapp.ripapp.utilities.SearchSorting;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class FilterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //TODO fare classe cities e aggiornare
    //@OneToMany
    //private List<CityEntity> cities;
    private String sortingVal;
    private Integer offsetVal;
    private Integer limitVal;
}
