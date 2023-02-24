package com.donato.challenge.service.implementation;

import com.donato.challenge.entities.ApiCallRequestHistory;
import com.donato.challenge.entities.Respuesta;
import com.donato.challenge.service.interfaces.ApiCallRequestHistoryService;
import com.donato.challenge.service.interfaces.OperationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImp implements OperationService {

    @Autowired
    private ExternalServiceImp externalServiceImp;

    @Autowired
    private ApiCallRequestHistoryService apiCallRequestHistoryService;


    @Override
    public double add(Double x, Double y) throws JsonProcessingException {

        double result=0;
        try {
           result=externalServiceImp.getPorcentual(x, y);
        }catch (Exception e){
            ApiCallRequestHistory op=  apiCallRequestHistoryService.findFirstByOrderByTimestampDesc();
            ObjectMapper mapper= new ObjectMapper();
            Respuesta respuesta=mapper.readValue(op.getResponseBody(), Respuesta.class);
            return respuesta.getRespuesta();

        }

        //Si falla se debe devolver el último valor retornado. En caso que no haya valor
        // retorna un error la API
        //persistirHistorial(new CalculoRequest(x, y, result));
        return  result;
    }



}
