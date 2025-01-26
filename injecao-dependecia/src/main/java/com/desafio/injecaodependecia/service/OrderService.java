package com.desafio.injecaodependecia.service;

import com.desafio.injecaodependecia.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private ShippingService service;

    public Double total(Order order) {
        Double calculateDiscount = order.getDiscount() / 100 * order.getBasic();
        return order.getBasic() - calculateDiscount + service.shipment(order.getBasic());
    }
}
