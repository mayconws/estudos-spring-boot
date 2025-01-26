package com.desafio.injecaodependecia.service;

import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    private static Double VALOR_FRETE_20_REAIS = 20.00;
    private static Double VALOR_FRETE_12_REAIS = 12.00;
    private static Double VALOR_FRETE_GRATIS = 0.00;

    public Double shipment(Double amount) {
        if (amount < 100) {
            return VALOR_FRETE_20_REAIS;
        }
        if (amount >= 100 && amount < 200) {
            return VALOR_FRETE_12_REAIS;
        } else {
            return VALOR_FRETE_GRATIS;
        }
    }

}
