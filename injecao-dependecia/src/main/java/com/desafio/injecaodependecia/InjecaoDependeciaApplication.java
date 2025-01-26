package com.desafio.injecaodependecia;

import com.desafio.injecaodependecia.model.Order;
import com.desafio.injecaodependecia.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;
import java.util.Scanner;

@SpringBootApplication
public class InjecaoDependeciaApplication implements CommandLineRunner {

    @Autowired
    private OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(InjecaoDependeciaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Locale.setDefault(Locale.US);
        Scanner entrada = new Scanner(System.in);

        Order order = new Order();

        System.out.println("Digite o código do pedido: ");
        order.setCode(entrada.nextInt());

        System.out.println("Digite o valor: ");
        order.setBasic(entrada.nextDouble());

        System.out.println("Digite a porcentagem de desconto: ");
        order.setDiscount(entrada.nextDouble());

        Double valorTotal = orderService.total(order);

        System.out.printf("Pedido código: %d \nValor total: R$ %.2f", order.getCode(), valorTotal);

        entrada.close();
    }
}
