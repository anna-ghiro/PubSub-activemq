package com.example.copelandactivemq.service;

import com.example.copelandactivemq.exception.UnauthorizedException;
import com.example.copelandactivemq.model.TopicName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

@Service
public class PublisherService {

    @Value("${spring.activemq.fantasyTopic}")
    String fantasyTopic;

    @Value("${spring.activemq.crimeTopic}")
    String crimeTopic;

    @Value("${spring.activemq.actionTopic}")
    String actionTopic;

    @Autowired
    JmsTemplate jmsTemplate;

    public String publishToTopic(String inputMessage, TopicName topic, String key) {

        boolean authorized = key.equals("admin");

        if (!authorized) {
            switch (topic) {
                case FantasyNameTopic -> authorized = key.equals("fantasyPrivateKey");
                case CrimeNameTopic -> authorized = key.equals("crimePrivateKey");
                case ActionNameTopic -> authorized = key.equals("actionPrivateKey");
                default -> {
                    return "topic does not exist";
                }
            }
        }

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
        } else {
            throw new UnauthorizedException("You are not authorized");
        }
        return null;
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
        return inputMessage;
    }

}
