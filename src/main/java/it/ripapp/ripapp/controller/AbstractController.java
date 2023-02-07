package it.ripapp.ripapp.controller;

import it.ripapp.ripapp.bll.TextBLL;
import it.ripapp.ripapp.entities.IEntity;
import it.ripapp.ripapp.entities.LocalizedEntity;
import it.ripapp.ripapp.jooqgen.enums.Lang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public class AbstractController {

    protected TextBLL textBLL;

    @Autowired
    public AbstractController(TextBLL textBLL) {
        this.textBLL = textBLL;
    }


    protected ResponseEntity GetResponse(Collection<? extends LocalizedEntity> entities, Lang lang, HttpStatus httpStatus) {
        entities.forEach(e -> e.resolveTexts(textBLL, lang));
        return GetResponse(entities, lang, httpStatus);
    }

    protected ResponseEntity GetResponse(Collection<? extends IEntity> entities, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(entities);
    }

    protected ResponseEntity GetResponse(LocalizedEntity entity, Lang lang, HttpStatus httpStatus) {
        entity.resolveTexts(textBLL, lang);
        return GetResponse(entity, lang, httpStatus);
    }


    protected ResponseEntity GetResponse(IEntity entity, HttpStatus httpStatus) {

//        try {
//            logManager.addResponseBody(Thread.currentThread().getId(), jacksonObjectMapper.writeValueAsString(entity));
//        } catch (Exception e){
//            e.printStackTrace();
//        }


        return ResponseEntity.status(httpStatus).body(entity);
    }

    protected ResponseEntity GetResponse(Boolean result, HttpStatus httpStatus) {

//        try {
//            logManager.addResponseBody(Thread.currentThread().getId(), jacksonObjectMapper.writeValueAsString(entity));
//        } catch (Exception e){
//            e.printStackTrace();
//        }


        return ResponseEntity.status(httpStatus).body(result);
    }

//    protected ResponseEntity GetResponseDBMessage(IEntity entity, Lang lang, ResponseEntity.BodyBuilder builder){
//
//        String localizedMessage = textBLL.getTextByTextIdLang(
//                entity.getHttpStatus().equals(HttpStatus.INTERNAL_SERVER_ERROR)
//                        ? MessagesTextIDs.server_error
//                        : entity.getMessage(),
//                lang);
//
//        return builder.body(Strings.isNullOrEmpty(localizedMessage) ? entity.getMessage() : localizedMessage);
//    }


}
