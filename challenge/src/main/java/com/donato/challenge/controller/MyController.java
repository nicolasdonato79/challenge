package com.donato.challenge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private double lastPercentage = 0.0; // guarda el último valor devuelto por el servicio externo
    private int retries = 0; // cuenta el número de intentos fallidos

    @GetMapping("/sum")
    public double sum(@RequestParam double num1, @RequestParam double num2) {
        double result = num1 + num2;
        double percentage = getPercentage(); // llama al método para obtener el porcentaje del servicio externo
        result += result * percentage / 100; // aplica el porcentaje a la suma
        return result;
    }

    private double getPercentage() {
        // intenta obtener el porcentaje del servicio externo
        try {
            return callExternalService();
        } catch (Exception e) {
            // si hay un error, devuelve el último valor devuelto por el servicio externo
            if (lastPercentage == 0.0) {
                throw new RuntimeException("No se puede obtener el porcentaje");
            }
            retries++;
            if (retries > 3) {
                throw new RuntimeException("Se agotaron los intentos para obtener el porcentaje");
            }
            return lastPercentage;
        }
    }

    private double callExternalService() {
        // aquí iría el código para llamar al servicio externo y obtener el porcentaje
        // por ahora, usaremos un valor aleatorio entre 0 y 10 como ejemplo
        double percentage = Math.random() * 10;
        lastPercentage = percentage;
        retries = 0;
        return percentage;
    }

}