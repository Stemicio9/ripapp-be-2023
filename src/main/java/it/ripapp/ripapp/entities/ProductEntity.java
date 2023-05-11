package it.ripapp.ripapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity implements IEntity{

    private UUID productId;

    private String productName;

    private double price;

    private String urlImage;

}
