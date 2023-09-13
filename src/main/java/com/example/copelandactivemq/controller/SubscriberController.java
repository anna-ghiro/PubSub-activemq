package com.example.copelandactivemq.controller;

import com.example.copelandactivemq.service.ConsumerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriberController {

    private final ConsumerService consumerService;

    @Autowired
    public SubscriberController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @PostMapping(path = "/subscribe")
    @Operation(summary = "Subscribe to a topic. Returns a key.")
    public String subscribeToTopic(@RequestParam("topic") String topic) {
        return consumerService.subscribeToTopic(topic);
    }
}
