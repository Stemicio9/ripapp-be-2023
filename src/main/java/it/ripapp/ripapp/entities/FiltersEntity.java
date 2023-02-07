package it.ripapp.ripapp.entities;

import it.ripapp.ripapp.utilities.SearchSorting;

import java.util.List;
import java.util.UUID;

public class FiltersEntity {

    private List<UUID> cities;
    private SearchSorting sorting;
    private int offset;
    private int limit;

    public List<UUID> getCities() {
        return cities;
    }

    public FiltersEntity setCities(List<UUID> cities) {
        this.cities = cities;
        return this;
    }

    public SearchSorting getSorting() {
        return sorting;
    }

    public FiltersEntity setSorting(SearchSorting sorting) {
        this.sorting = sorting;
        return this;
    }

    public int getOffset() {
        return offset;
    }

    public FiltersEntity setOffset(int offset) {
        this.offset = offset;
        return this;
    }

    public int getLimit() {
        return limit;
    }

    public FiltersEntity setLimit(int limit) {
        this.limit = limit;
        return this;
    }
}
