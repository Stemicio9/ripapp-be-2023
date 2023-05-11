package it.ripapp.ripapp.EntityUpdate;

import jdk.jfr.Enabled;
import lombok.*;

import javax.persistence.Entity;
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
    private UUID productId;

    private String productName;

    private double price;

    private String urlImage;
}
