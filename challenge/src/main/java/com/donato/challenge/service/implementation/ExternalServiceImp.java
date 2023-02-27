package com.donato.challenge.service.implementation;

import com.donato.challenge.exception.ServerExternalException;
import com.donato.challenge.service.interfaces.ExternalService;
import org.springframework.cache.interceptor.CacheOperationInvoker;
import org.springframework.stereotype.Service;

@Service
public class ExternalServiceImp implements ExternalService {

    public double getPorcentual(Double x, Double y) throws ServerExternalException {

        if(x==0 && y ==0){
            throw new ServerExternalException("Error al llamar servicio externo");
        }


        return (x+y) * 1.1;
    }

}