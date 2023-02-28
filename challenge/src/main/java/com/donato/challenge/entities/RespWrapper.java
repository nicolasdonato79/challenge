package com.donato.challenge.entities;

import org.springframework.http.HttpStatus;

public class RespWrapper {

    private Resp resp;
    private HttpStatus status;

    public RespWrapper(Resp resp, HttpStatus status) {
        this.resp = resp;
        this.status = status;
    }

    public RespWrapper(Resp resp) {
        this.resp = resp;
    }

    public Resp getResp() {
        return resp;
    }

    public void setResp(Resp resp) {
        this.resp = resp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
