package it.ripapp.ripapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactEntity {
    private String num;
    private String name;
    private String phonehash;
    private String prefix;


}
