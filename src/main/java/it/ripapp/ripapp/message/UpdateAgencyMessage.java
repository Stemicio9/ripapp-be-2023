package it.ripapp.ripapp.message;

import it.ripapp.ripapp.entityUpdate.AgencyEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAgencyMessage extends BaseAgencyMessage {
    AgencyEntity agencyUpdated;

}
