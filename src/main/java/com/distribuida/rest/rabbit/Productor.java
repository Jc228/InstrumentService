package com.distribuida.rest.rabbit;

import com.distribuida.rest.Aplicacion;
import com.distribuida.rest.entidades.Instrument;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Productor {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public Productor (final RabbitTemplate rabbitTemplate){
       this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMensaje(Instrument instrument){
        System.out.println("Enviando Instrument a Rabbit...");
        rabbitTemplate.convertAndSend(Aplicacion.EXCHANGE_NAME, Aplicacion.ROUTING_KEY, instrument);
        System.out.println("Enviado a Rabbbit...");
    }
}
