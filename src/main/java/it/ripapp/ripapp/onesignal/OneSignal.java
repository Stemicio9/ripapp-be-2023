package it.ripapp.ripapp.onesignal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.ripapp.ripapp.bll.UserBLL;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class OneSignal {

    @Value("${onesignal.appid}")
    private String appid;

    @Value("${onesignal.apikey}")
    private String apikey;

    private final OkHttpClient client;
    private final ObjectMapper jacksonObjectMapper;

    private final UserBLL userBLL;


    @Autowired
    public OneSignal(ObjectMapper jacksonObjectMapper, UserBLL userBLL) {
        client = new OkHttpClient();
        this.jacksonObjectMapper = jacksonObjectMapper;
        this.userBLL = userBLL;
    }




    public void sendNotification(OSNotification notification) {

        notification.setAppID(appid);

        String notifString;
        try {
            notifString =  jacksonObjectMapper.writeValueAsString(notification);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }


        Request request = new Request.Builder()
                .url("https://onesignal.com/api/v1/notifications")
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Authorization", "Basic "+apikey)
                .post(RequestBody.create(notifString, MediaType.parse("application/json; charset=utf-8")))
                .build();

        try (Response response = client.newCall(request).execute()) {

            if (response.code() == 200){
                var result = jacksonObjectMapper.readValue(response.body().string(), OSResponse.class);

                if (result.getErrors() != null && result.getErrors().getInvalid_player_ids() != null && result.getErrors().getInvalid_player_ids().size() > 0)
                    userBLL.deleteInstanceIDs(result.getErrors().getInvalid_player_ids());
            }
            else {
                System.out.println("ERRORE ONESIGNAL "+response.code()+" "+response.body().string());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}


