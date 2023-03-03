package com.donato.challenge.service.interfaces;

import com.donato.challenge.wrapper.RespWrapper;
import com.donato.challenge.exception.ApiHistoryIOException;
import com.donato.challenge.exception.ServerExternalException;
import com.fasterxml.jackson.core.JsonProcessingException;


public interface OperationService {

   RespWrapper add (Double x, Double y) throws ApiHistoryIOException, ServerExternalException, JsonProcessingException;

  }
