package com.donato.challenge.service.implementation;

import com.donato.challenge.service.interfaces.ExternalService;
import org.springframework.stereotype.Service;

@Service
public class ExternalServiceImp implements ExternalService {

    public double getPorcentual(Double x, Double y) {

        return (x+y) * 1.1;
    }

}