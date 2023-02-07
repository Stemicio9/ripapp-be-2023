package it.ripapp.ripapp.dal;

import it.ripapp.ripapp.entities.DemiseEntity;
import it.ripapp.ripapp.entities.DemiseMatchEntity;
import it.ripapp.ripapp.jooqgen.Tables;
import it.ripapp.ripapp.utilities.SearchSorting;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DemiseDAL extends AbstractDAL {

    public DemiseDAL(DSLContext dsl) {
        super(dsl);
    }


    public List<DemiseEntity> getDemisesByCitiesWithSorting(List<UUID> cities, SearchSorting sorting, int offset, int size, UUID accountid) {

        var sortedBy = switch (sorting) {
            case date -> Tables.DEMISE.DATE.desc();
            case name -> Tables.DEMISE.NAME.asc();
            case surname -> Tables.DEMISE.SURNAME.asc();
            case timestamp -> Tables.DEMISE.TS.desc();
        };

        var demises = dsl.selectFrom(Tables.DEMISE)
                .where(Tables.DEMISE.DEMISEID.in(
                        dsl.selectDistinct(Tables.DEMISE_CITY.DEMISEID).from(Tables.DEMISE_CITY).where(Tables.DEMISE_CITY.CITYID.in(cities)))
                )
                .orderBy(sortedBy)
                .limit(offset+size)
                .stream()
                .skip(offset)
                .map(r -> r.into(DemiseEntity.class))
                .collect(Collectors.toMap(
                        DemiseEntity::getDemiseid,
                        x -> x
                ));

        var matches = dsl.selectFrom(Tables.DEMISE_MATCH)
                .where(Tables.DEMISE_MATCH.ACCOUNTID.eq(accountid))
                .fetchMap(
                        x -> x.getDemiseid(),
                        x -> x.into(DemiseMatchEntity.class)
                );

        return demises.values().stream()
                .peek(d -> d.setMatch(matches.getOrDefault(d.getDemiseid(), null)))
                .collect(Collectors.toList());
    }


    //TODO verificare se ricerca solo tra i propri (in caso usare tabella match)
    public Set<DemiseEntity> searchDemisesBySurname(String query) {
        return dsl.select()
                .from(Tables.DEMISE)
                .where(Tables.DEMISE.SURNAME.likeIgnoreCase(query+"%"))
                .fetchSet(x -> x.into(DemiseEntity.class));
    }
}
