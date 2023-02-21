package com.donato.challenge.entities;

import lombok.Data;
import org.springframework.stereotype.Component;


@Component
@Data
public class OperationRequest {

    private double x;
    private double y;


}
