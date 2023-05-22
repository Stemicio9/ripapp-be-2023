/*
 * This file is generated by jOOQ.
 */
package it.ripapp.ripapp.entityUpdate;
import it.ripapp.ripapp.bll.Kinship;
import it.ripapp.ripapp.bll.Lang;
import it.ripapp.ripapp.entityUpdate.compositeKeys.KinshipTextKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

//classe duplicata? è uguale a NotificationKinshipText
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(KinshipTextKey.class)
public class KinshipText {
    @Id
    private Kinship KINSHIP;
    @Id
    private Lang LANG;
    private String TEXT;
}
