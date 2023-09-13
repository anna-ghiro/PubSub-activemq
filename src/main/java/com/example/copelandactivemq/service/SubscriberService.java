package com.example.copelandactivemq.service;

import com.example.copelandactivemq.model.TopicName;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Service
public class SubscriberService {

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

    public String subscribeToTopic(TopicName topic) {

        switch (topic) {
            case FantasyNameTopic -> { return "fantasyPrivateKey"; }
            case CrimeNameTopic -> { return "crimePrivateKey"; }
            case ActionNameTopic -> { return "actionPrivateKey"; }
            default -> { return "This topic does not exist"; }
        }
    }
}
