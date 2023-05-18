package it.ripapp.ripapp.EntityUpdate;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NotificationText {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lang;

    private String text;
}
