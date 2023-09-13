package com.example.copelandactivemq.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.Map;

@Component
public class ConsumerService {

    //TODO change name to subscriber

    @JmsListener(destination = "FantasyNameTopic")
    @JmsListener(destination = "CrimeNameTopic")
    @JmsListener(destination = "ActionNameTopic")
    public void receiveMessageFromTopic(final Message jsonMessage) throws JMSException {
        String messageData;
        System.out.println("Received message in topic " + jsonMessage.getJMSDestination());
        if(jsonMessage instanceof TextMessage textMessage) {
            messageData = textMessage.getText();
            System.out.println("The message received is:" + messageData);
        }
    }

    public String subscribeToTopic(String topic) {

        switch (topic) {
            case "FantasyNameTopic" -> { return "fantasyPrivateKey"; }
            case "CrimeNameTopic" -> { return "crimePrivateKey"; }
            case "ActionNameTopic" -> { return "actionPrivateKey"; }
            default -> { return "This topic does not exist"; }
        }
    }
}
