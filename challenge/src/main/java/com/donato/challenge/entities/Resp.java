package com.donato.challenge.entities;


import org.springframework.http.HttpStatus;

public class Resp {
    Double resp;


    public Resp() {

    }
    public Resp(Double resp) {
        this.resp = resp;
    }

    public Double getResp() {
        return resp;
    }

    public void setResp(Double resp) {
        this.resp = resp;
    }


}
