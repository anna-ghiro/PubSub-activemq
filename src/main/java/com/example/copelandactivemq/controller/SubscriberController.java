package com.example.copelandactivemq.controller;

import com.example.copelandactivemq.model.TopicName;
import com.example.copelandactivemq.service.SubscriberService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriberController {

    private final SubscriberService subscriberService;

    @Autowired
    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @PostMapping(path = "/subscribe")
    @Operation(summary = "Subscribe to a topic. Returns a key.")
    public String subscribeToTopic(@RequestParam("topic") TopicName topic) {
        return subscriberService.subscribeToTopic(topic);
    }
}
