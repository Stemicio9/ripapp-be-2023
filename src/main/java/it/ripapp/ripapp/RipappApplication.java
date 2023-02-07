package it.ripapp.ripapp;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import it.ripapp.ripapp.bll.MiscBLL;
import it.ripapp.ripapp.utilities.CustomInterceptor;
import it.ripapp.ripapp.utilities.LogManager;
import it.ripapp.ripapp.utilities.LoggingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.InputStream;
import java.util.concurrent.ThreadFactory;

@SpringBootApplication
public class RipappApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(RipappApplication.class, args);
    }


    @Autowired
    private CustomInterceptor customInterceptor;

    @Autowired
    private MiscBLL miscBLL;

    @Autowired
    private LogManager logManager;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customInterceptor).addPathPatterns("/**");
    }


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedHeader("x-firebase-auth");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }


    @Bean
    public FirebaseAuth firebaseAuth() {
        try {
            InputStream inputstream = RipappApplication.class.getResourceAsStream("/ripapp-6cb0e-firebase-adminsdk-4uez1-62cbd67496.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(inputstream))
                    .setDatabaseUrl("https://ripapp-6cb0e.firebaseio.com")
                    .setStorageBucket("ripapp-6cb0e.appspot.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            System.out.println("firebase initialization ERROR");
            e.printStackTrace();
            return null;
        }

        return FirebaseAuth.getInstance();
    }


    @Bean
    public RingBuffer<LoggingEvent> ringBuffer(){
        ThreadFactory threadFactory = DaemonThreadFactory.INSTANCE;
        Disruptor<LoggingEvent> disruptor = new Disruptor<>(LoggingEvent.EVENT_FACTORY, 32, threadFactory, ProducerType.MULTI, new BlockingWaitStrategy());
        disruptor.handleEventsWith((event, sequence, endOfBatch) -> logManager.logEvent(event));
        return disruptor.start();
    }


    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println(miscBLL.getServerStatus());
        System.out.println(miscBLL.getSupportedAppVersions());
    }

}
