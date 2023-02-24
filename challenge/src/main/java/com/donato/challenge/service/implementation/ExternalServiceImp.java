package com.donato.challenge.service.implementation;

import com.donato.challenge.service.interfaces.ExternalService;
import org.springframework.cache.interceptor.CacheOperationInvoker;
import org.springframework.stereotype.Service;

@Service
public class ExternalServiceImp implements ExternalService {

    public double getPorcentual(Double x, Double y) throws Exception {

        if(x==0 && y ==0){
            throw new Exception("Error al llamar servicio externo");
        }


        return (x+y) * 1.1;
    }

}