package com.donato.challenge.wrapper;

import com.donato.challenge.entities.Resp;
import org.springframework.http.HttpStatus;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RespWrapper that = (RespWrapper) o;
        return Objects.equals(resp.getResp(), that.resp.getResp()) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(resp, status);
    }
}
