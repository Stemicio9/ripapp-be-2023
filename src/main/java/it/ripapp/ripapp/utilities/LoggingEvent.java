package it.ripapp.ripapp.utilities;

import com.lmax.disruptor.EventFactory;

import java.time.LocalDateTime;
import java.util.UUID;

public class LoggingEvent {

    private UUID id;
    private String reqid;
    private LocalDateTime timestamp;
    private String method;
    private String url;
    private String querystring;
    private String userid;
    private Integer status;
    private Long executiontime;
    private String response;
    private String stacktrace;
    private String exMessage;

    private LoggingEventType type;

    public final static EventFactory<LoggingEvent> EVENT_FACTORY = () -> new LoggingEvent();


    public String getReqid() {
        return reqid;
    }

    public LoggingEvent setReqid(String reqid) {
        this.reqid = reqid;
        return this;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public LoggingEvent setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public LoggingEvent setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public LoggingEvent setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getQuerystring() {
        return querystring;
    }

    public LoggingEvent setQuerystring(String querystring) {
        this.querystring = querystring;
        return this;
    }

    public String getUserid() {
        return userid;
    }

    public Integer getStatus() {
        return status;
    }

    public LoggingEvent setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public LoggingEventType getType() {
        return type;
    }

    public LoggingEvent setType(LoggingEventType type) {
        this.type = type;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public LoggingEvent setId(UUID id) {
        this.id = id;
        return this;
    }

    public LoggingEvent setUserid(String userid) {
        this.userid = userid;
        return this;
    }

    public Long getExecutiontime() {
        return executiontime;
    }

    public LoggingEvent setExecutiontime(Long executiontime) {
        this.executiontime = executiontime;
        return this;
    }

    public String getResponse() {
        return response;
    }

    public LoggingEvent setResponse(String response) {
        this.response = response;
        return this;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public LoggingEvent setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
        return this;
    }

    public String getExMessage() {
        return exMessage;
    }

    public LoggingEvent setExMessage(String exMessage) {
        this.exMessage = exMessage;
        return this;
    }


    public void reset(){
        id = null;
        reqid = null;
        timestamp = null;
        method = null;
        url = null;
        querystring = null;
        userid = null;
        status = null;
        executiontime = null;
        response = null;
        stacktrace = null;
        exMessage = null;
    }
}
