package it.ripapp.ripapp.entities;

import it.ripapp.ripapp.utilities.SearchSorting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FiltersEntity {
    private List<UUID> cities;
    private SearchSorting sorting;
    private int offset;
    private int limit;
}
