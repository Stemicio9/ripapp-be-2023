package it.ripapp.ripapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgencyEntity implements IEntity {

    private UUID agencyid;
    private String name;
    private String address;
    private String logo;

    @JsonIgnore
    private Double similarity;

    @JsonIgnore
    private String email;


}
