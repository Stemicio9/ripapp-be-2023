package it.ripapp.ripapp.message;

import it.ripapp.ripapp.entityUpdate.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeleteProductMessage {
        String message;
        ProductEntity productDeleted;
}
