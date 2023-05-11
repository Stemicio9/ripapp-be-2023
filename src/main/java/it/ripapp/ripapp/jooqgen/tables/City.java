/*
 * This file is generated by jOOQ.
 */
package it.ripapp.ripapp.jooqgen.tables;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @Id
    private UUID CITYID;
    private String NAME;
    private String LON;
    private String LAT;
}
