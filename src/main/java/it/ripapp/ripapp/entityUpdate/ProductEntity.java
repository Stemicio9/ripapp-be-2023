package it.ripapp.ripapp.entityUpdate;
import it.ripapp.ripapp.dto.ProductOffered;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    private double price;

    private String urlImage;

    private String firebaseId;

    @Override
    public boolean equals(Object other){
        if(other == this) return true;
        if(!(other instanceof ProductOffered)) return false;
        ProductEntity o = (ProductEntity) other;
        return this.productId.equals(o.getProductId());
    }
}
