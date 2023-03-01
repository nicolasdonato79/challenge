package com.donato.challenge.service.implementation;

import com.donato.challenge.exception.ServerExternalException;
import com.donato.challenge.service.interfaces.ExternalService;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class ExternalServiceImp implements ExternalService {

    private double number;
    private Long lastTime;

    // Uso 1 minutos para testear. Reemplazar el 1 por otro número cuando deseen testear otro valor.
    private final Long LAP = Long.valueOf(1 * 60 * 1000);
    private Random random;

    ExternalServiceImp() {
        this.random = new Random();
        startTimer();
    }

    public double getPorcentual(Double x, Double y) throws ServerExternalException {

        if (x == 0 && y == 0) {
            throw new ServerExternalException("Error al llamar servicio externo");
        }


        return (x + y) * number;
    }


    private void updateNumber() {
        long now = System.currentTimeMillis();
        if (lastTime == null || now - lastTime >= LAP) {
            this.number = 1 + (this.random.nextDouble() * (1.99 - 1));
            this.lastTime = now;
        }
    }

    private void startTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateNumber();
            }
        }, 0, LAP);
    }

}