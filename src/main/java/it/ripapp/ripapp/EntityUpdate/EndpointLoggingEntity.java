package it.ripapp.ripapp.entityUpdate;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class EndpointLoggingEntity {
    @Id
    private UUID id;
    private String requid;
    private LocalDateTime timestamp;
    private String method;
    private String url;
    private String quesrystring;
    private String userid;
    private String body;
    private Integer status;
    private Integer executiontime;
    private String exceptionstacktrace;
    private String message;
    private String response;

}
