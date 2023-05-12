package it.ripapp.ripapp.bll;


import it.ripapp.ripapp.entities.DemiseEntity;
import it.ripapp.ripapp.entities.FiltersEntity;

import it.ripapp.ripapp.repository.AccountRepository;
import it.ripapp.ripapp.repository.DemiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static it.ripapp.ripapp.bll.Demisematchtype.*;

@Service
public class DemiseBLL {

    private final int searchSize = 20;
    private DemiseRepository demiseDAL;
    private AccountRepository userDAL;
    private UserBLL userBLL;

    @Autowired
    public DemiseBLL(DemiseRepository demiseDAL, AccountRepository userDAL, UserBLL userBLL) {
        this.demiseDAL = demiseDAL;
        this.userDAL = userDAL;
        this.userBLL = userBLL;
    }
    /*

    public List<DemiseEntity> getDemisesByFilters(FiltersEntity filters, UUID accountid, Lang lang){

        if (filters.getCities() == null || filters.getCities().isEmpty())
            filters.setCities(userDAL.findAllByCitY_CityId(accountid));

        List<DemiseEntity> results = demiseDAL.getDemisesByCitiesWithSorting(filters.getCities(), filters.getSorting(), filters.getOffset(), searchSize, accountid);

        for (DemiseEntity d : results) {

            Demisematchtype contact;
            if (d.getMatch() != null)
                switch (d.getMatch().getType()){

                    case contact -> {
                        d.setTitle(d.getMatch().getName());
                    }

                    case relative -> {
                        d.setTitle(d.getSurname() + " " + d.getName());
                        d.setKinshipdesc(userBLL.computeKinshipDesc(d.getMatch().getName(), d.getMatch().getKinship(), lang));
                    }

                    case city -> {
                        d.setTitle(d.getSurname() + " " + d.getName());
                    }
                }
            else
                d.setTitle(d.getSurname() + " " + d.getName());

        }


        return results;
    }



    public List<DemiseEntity> getUserLinkedDemises(UUID userid, FiltersEntity filtersEntity, Lang lang) {

        var matches = userDAL.getAccountMatches(userid);

        List<it.ripapp.ripapp.entityUpdate.DemiseEntity> demises = userDAL.getDemisesByIDs(matches.keySet());

        for (it.ripapp.ripapp.entityUpdate.DemiseEntity d : demises) {

            Demisematchtype m = matches.get(d.getDemiseid());

            switch (m.getType()){

                case contact -> {
                    d.setTitle(m.getName());
                }

                case relative -> {
                    d.setTitle(d.getSurname() + " " + d.getName());
                    d.setKinshipdesc(userBLL.computeKinshipDesc(m.getName(), m.getKinship(), lang));
                }

                case city -> {
                    d.setTitle(d.getSurname() + " " + d.getName());
                }
            }

        }

        return demises.stream()
                .sorted((a, b) -> {
                    switch (filtersEntity.getSorting()) {
                        case date -> {
                            return b.getTs().compareTo(a.getTs());
                        }
                        case name -> {
                            return a.getName().compareTo(b.getName());
                        }
                        case surname -> {
                            return a.getSurname().compareTo(b.getSurname());
                        }
                        case timestamp -> {
                            return b.getTs().compareTo(a.getTs());
                        }
                    }

                    return 0;
                })
                .skip(filtersEntity.getOffset())
                .limit(20)
                .collect(Collectors.toList());
    }

     */

//    public List<DemiseEntity> getUserLinkedDemises(UUID userid, FiltersEntity filtersEntity, Lang lang) {
//        var demises = userDAL.getUserLinkedDemises(userid, filtersEntity.setLimit(20));
//
//        for (DemiseEntity d : demises) {
//            if (d.getKinship() != null)
//            {
//                d.setTitle(d.getName()+" "+d.getSurname());
//                d.setKinshipdesc(userBLL.computeKinshipDesc(d.getRelativename(), d.getKinship(), lang));
//            }
//            else
//            {
//                d.setTitle(d.getName());
//                d.setKinshipdesc(d.getSurname());
//            }
//        }
//
//        return demises;
//    }

    public Set<DemiseEntity> userDemisesAutocomplete(UUID userid, String query) {
        Set<DemiseEntity> result = (Set<DemiseEntity>) demiseDAL.findAllBySurnameContains(query);
        return result;
    }
}
