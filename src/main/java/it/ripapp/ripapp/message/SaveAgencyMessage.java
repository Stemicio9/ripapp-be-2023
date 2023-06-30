package it.ripapp.ripapp.message;

import it.ripapp.ripapp.entityUpdate.AgencyEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveAgencyMessage {
    String message;
    AgencyEntity agencySaved;
}