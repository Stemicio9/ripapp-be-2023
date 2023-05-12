package it.ripapp.ripapp.controller;

import org.apache.commons.codec.language.bm.Lang;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public class AbstractController {




    protected ResponseEntity GetResponse(Collection entities, Lang lang, HttpStatus httpStatus) {
  //      entities.forEach(e -> e.resolveTexts(textBLL, lang));
        return GetResponse(entities, lang, httpStatus);
    }

    protected ResponseEntity GetResponse(Collection entities, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(entities);
    }

    protected ResponseEntity GetResponse(Object entity, Lang lang, HttpStatus httpStatus) {
    //    entity.resolveTexts(textBLL, lang);
        return GetResponse(entity, lang, httpStatus);
    }


    protected ResponseEntity GetResponse(Object entity, HttpStatus httpStatus) {

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
