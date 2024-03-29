package it.ripapp.ripapp.controller;


import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionController extends AbstractController {


    /*
    @Autowired
    private RingBuffer<LoggingEvent> ringBuffer;

    @Autowired
    private RequestIDs requestIDs;


    @Value("${backend.env.test}")
    private boolean test;


    @Autowired
    public ExceptionController(TextBLL textBLL) {
        super(textBLL);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity internalServerErrorResponse(Exception ex) {
        logRequestUpdateException(requestIDs.getReqID(Thread.currentThread().getId()).toString(), getExceptionStackTrace(ex), MessagesTextIDs.server_error);

        if (test)
            ex.printStackTrace();

        return new ResponseEntity<>(MessagesTextIDs.server_error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity internalServerErrorResponse(HttpRequestMethodNotSupportedException ex) {
        logRequestUpdateException(requestIDs.getReqID(Thread.currentThread().getId()).toString(), getExceptionStackTrace(ex), "HttpRequestMethodNotSupportedException");

        if (test)
            ex.printStackTrace();

        return new ResponseEntity<>(MessagesTextIDs.server_error, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity badRequestException(BadRequestException ex, HttpServletRequest request) {
        Lang lang = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("lang")).map(c -> getLangByString(c.getValue())).findFirst().get();
        String message = textBLL.getTextByTextIdLang(ex.getMessage(), lang);

        if (test)
            ex.printStackTrace();

        logRequestUpdateException(requestIDs.getReqID(Thread.currentThread().getId()).toString(), getExceptionStackTrace(ex), message);

        return new ResponseEntity<>(textBLL.getTextByTextIdLang(ex.getMessage(), lang), HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(value = {DataAccessException.class})
    public ResponseEntity dataAccessException(DataAccessException ex, HttpServletRequest request) {
        Lang lang = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("lang")).map(c -> getLangByString(c.getValue())).findFirst().get();
        String message = textBLL.getTextByTextIdLang(ex.getMessage(), lang);

        if (test)
            ex.printStackTrace();

        logRequestUpdateException(requestIDs.getReqID(Thread.currentThread().getId()).toString(), getExceptionStackTrace(ex), message);

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private String getExceptionStackTrace(Exception ex) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }


    private Lang getLangByString(String value){
        return switch (value){
            case "it" -> Lang.it_;
            case "en" -> Lang.en;
            default -> Lang.it_;
        };
    }



    private void logRequestUpdateException(String reqid, String stackTrace, String message){
        final long seq = ringBuffer.next();

        final LoggingEvent valueEvent = ringBuffer.get(seq);
        valueEvent.reset();
        valueEvent.setReqid(reqid);
        valueEvent.setStacktrace(stackTrace);
        valueEvent.setExMessage(message);
        valueEvent.setType(LoggingEventType.EXCEPTION);

        ringBuffer.publish(seq);
    }
*/

}

