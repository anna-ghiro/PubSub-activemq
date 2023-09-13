package com.example.copelandactivemq.controller;

import com.example.copelandactivemq.model.TopicName;
import com.example.copelandactivemq.service.ProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublisherController {

    private final ProducerService producerService;

    @Autowired
    public PublisherController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping(path = "/publish")
    @Operation(summary = "Send a message to subscribers. You will need the private key")
    public String publishToTopic(@RequestBody String inputMessage,
                                 @RequestParam("topic") TopicName topic,
                                 @RequestParam("key") String key) throws JsonProcessingException {
        return producerService.publishToTopic(inputMessage, topic, key);
    }
}
