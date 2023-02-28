package com.donato.challenge.service.implementation;

import com.donato.challenge.entities.ApiCallRequestHistory;
import com.donato.challenge.entities.Resp;
import com.donato.challenge.entities.RespWrapper;
import com.donato.challenge.exception.ApiHistoryIOException;
import com.donato.challenge.exception.ServerExternalException;
import com.donato.challenge.service.interfaces.ApiCallRequestHistoryService;
import com.donato.challenge.service.interfaces.OperationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImp implements OperationService {

    @Autowired
    private ExternalServiceImp externalServiceImp;

    @Autowired
    private ApiCallRequestHistoryService apiCallRequestHistoryService;


    @Override
    public RespWrapper add(Double x, Double y) throws ApiHistoryIOException ,ServerExternalException, JsonProcessingException {

        double result=0;
        try {
           result=externalServiceImp.getPorcentual(x, y);
        }catch (Exception e){
            ApiCallRequestHistory op=  apiCallRequestHistoryService.findLastSuccessfulResponse();

            if(op.getResponseBody()!=null&& op.getStatus()==200 ){
            ObjectMapper mapper= new ObjectMapper();
            Resp resp =mapper.readValue(op.getResponseBody(), Resp.class);
            return new RespWrapper(resp, HttpStatus.SERVICE_UNAVAILABLE);
            }else{
                throw new ServerExternalException("Error al consumir el servicio externo");
            }
        }
        return  new RespWrapper(new Resp(result) ,HttpStatus.OK) ;
    }



}
