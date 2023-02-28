package com.donato.challenge.service.interfaces;

import com.donato.challenge.entities.RespWrapper;
import com.donato.challenge.exception.ApiHistoryIOException;
import com.donato.challenge.exception.ServerExternalException;
import com.fasterxml.jackson.core.JsonProcessingException;


public interface OperationService {

   public RespWrapper add (Double x, Double y) throws ApiHistoryIOException, ServerExternalException, JsonProcessingException;

  }
