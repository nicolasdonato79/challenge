package com.donato.challenge.entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.util.Date;

@Entity

public class ApiCallRequestHistory {
    @Id
    @SequenceGenerator(name = "apiCallRequestHistorySeq", sequenceName = "apiCallRequestHistorySeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "apiCallRequestHistorySeq")
    private Long id;

    private Integer status;
    private String method;

    private String endpoint;

    private String requestBody;

    private String responseBody;

    private Date timestamp;

    public ApiCallRequestHistory(Integer status, String method, String endpoint, String requestBody, String responseBody, Date timestamp) {
        this.status= status;
        this.method = method;
        this.endpoint = endpoint;
        this.requestBody = requestBody;
        this.responseBody = responseBody;
        this.timestamp = timestamp;
    }

    public ApiCallRequestHistory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}