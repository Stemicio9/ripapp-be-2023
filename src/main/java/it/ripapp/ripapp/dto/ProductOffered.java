package it.ripapp.ripapp.dto;

import it.ripapp.ripapp.entityUpdate.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductOffered {

    private ProductEntity product;
    private Boolean offered;
}
