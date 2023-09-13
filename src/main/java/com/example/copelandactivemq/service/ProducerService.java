package com.example.copelandactivemq.service;

import com.example.copelandactivemq.model.TopicName;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

@Component
public class ProducerService {

    @Value("${spring.activemq.fantasyTopic}")
    String fantasyTopic;

    @Value("${spring.activemq.crimeTopic}")
    String crimeTopic;

    @Value("${spring.activemq.actionTopic}")
    String actionTopic;

    @Autowired
    JmsTemplate jmsTemplate;

    public String publishToTopic(String inputMessage, TopicName topic, String key) throws JsonProcessingException {

        //TODO validation for different topic key

        boolean authorized = key.equals("admin") || key.equals("fantasyPrivateKey")
                || key.equals("crimePrivateKey") || key.equals("actionPrivateKey");

        if (authorized) {
            switch (topic) {
                case FantasyNameTopic -> {
                    return sendMessage(inputMessage, fantasyTopic);
                }
                case CrimeNameTopic -> {
                    return sendMessage(inputMessage, crimeTopic);
                }
                case ActionNameTopic -> {
                    return sendMessage(inputMessage, actionTopic);
                }
            }
        }
        return "You are not authorized";
    }

    private String sendMessage(String inputMessage, String topic) {
        try {
            jmsTemplate.send(topic, messageCreator -> {
                TextMessage message = messageCreator.createTextMessage();
                message.setText(inputMessage);
                return message;
            });
        } catch (Exception ex) {
            System.out.println("ERROR in sending message to topic " + topic);
        }
        return null;
    }

}
