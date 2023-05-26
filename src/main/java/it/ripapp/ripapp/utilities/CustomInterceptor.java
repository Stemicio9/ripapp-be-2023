package it.ripapp.ripapp.utilities;

import com.google.common.base.Stopwatch;
import com.lmax.disruptor.RingBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Component
public class CustomInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(CustomInterceptor.class);

    private final HashMap<Long, Stopwatch> stopwatches = new HashMap<>();


    @Autowired
    private RequestIDs requestIDs;

    @Autowired
    private RingBuffer<LoggingEvent> ringBuffer;


    @Value("${backend.env.test}")
    private boolean test;



    private boolean isURIWhitelisted(String uri){
        return uri.equals("/healthcheck");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        UUID reqID = requestIDs.addRequestID(Thread.currentThread().getId());

        String logBody = request.getMethod()+" "+ request.getRequestURL();
        if (request.getQueryString() != null)
            logBody += " ? "+request.getQueryString();

        if (test)
            logger.info(logBody);

        String userCookie = "";

        if (request.getHeader("cookie") != null && !request.getHeader("cookie").isEmpty())
        {
            String[] cookies = request.getHeader("cookie").split(";");

            for (String cookie : cookies)
               /* if (cookie.split("=")[0].equals("userid"))
                    userCookie = cookie.split("=")[1]; */
            if(cookie.contains("firebasecookie")){
                userCookie = cookie.split("=")[1];
            }
        }

        String userid = null;

        if (!userCookie.isEmpty())
            userid = userCookie;

        logRequestInsert(
                requestIDs.getReqID(Thread.currentThread().getId()).toString(),
                LocalDateTime.now(ZoneOffset.UTC),
                request.getMethod(),
                request.getRequestURL().toString(),
                request.getQueryString(),
                userid
        );

        if (!isURIWhitelisted(request.getRequestURI()))
        {
//            String appversion = request.getHeader("app_version");
//            if (appversion == null)
//            {
//                response.sendRedirect("/outdatedapp");
//                return false;
//            }
//
//            Map<String, AppVersionEntity> versions = miscBLL.getSupportedAppVersions();
//            if (!versions.containsKey(appversion))
//            {
//                response.sendRedirect("/outdatedapp");
//                return false;
//            }
//
//
//            if (versions.get(appversion).getServertype().equals("test"))
//            {
//                response.sendRedirect("/usetest");
//                return false;
//            }
        }


//
//       if (true){
//
//           try {
//               String line = request.getReader().lines().collect(Collectors.joining());
//               System.out.println(line);
//               System.out.println();
//           } catch (Exception e){
//               return false;
//           }
//       }





        stopwatches.put(Thread.currentThread().getId(), Stopwatch.createStarted());


        return true;
    }




    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        Stopwatch stopwatch = stopwatches.remove(Thread.currentThread().getId());


        if (stopwatch.isRunning())
            stopwatch.stop();

        String logBody = request.getMethod()+" "+ request.getRequestURL();
        if (request.getQueryString() != null)
            logBody += " ? "+request.getQueryString();

        if (test)
            logger.info("\t----  "+response.getStatus() + "  -------- " + logBody + " ------ Time elapsed: " + stopwatch.toString()+"\n");

        logRequestUpdate(
                requestIDs.getReqID(Thread.currentThread().getId()).toString(),
                response.getStatus(),
                stopwatch.elapsed(TimeUnit.MILLISECONDS)
        );

        requestIDs.removeReqID(Thread.currentThread().getId());
    }



    private void logRequestUpdate(String reqid, int status, long executionTime){
        final long seq = ringBuffer.next();

        final LoggingEvent valueEvent = ringBuffer.get(seq);
        valueEvent.reset();
        valueEvent.setReqid(reqid);
        valueEvent.setStatus(status);
        valueEvent.setExecutiontime(executionTime);
        valueEvent.setType(LoggingEventType.UPDATE);

        ringBuffer.publish(seq);
    }


    private void logRequestInsert(String reqid, LocalDateTime timestamp, String method, String url, String querystring, String userid){
        final long seq = ringBuffer.next();

        final LoggingEvent valueEvent = ringBuffer.get(seq);
        valueEvent.reset();
        valueEvent.setReqid(reqid);
        valueEvent.setTimestamp(timestamp);
        valueEvent.setMethod(method);
        valueEvent.setUrl(url);
        valueEvent.setQuerystring(querystring);
        valueEvent.setUserid(userid);
        valueEvent.setType(LoggingEventType.INSERT);

        ringBuffer.publish(seq);
    }

}
