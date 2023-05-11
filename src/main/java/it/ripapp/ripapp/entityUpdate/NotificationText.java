package it.ripapp.ripapp.entityUpdate;

import lombok.*;

import javax.persistence.Entity;
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
    private Long lang;

    private String text;
}
