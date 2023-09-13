package com.example.copelandactivemq.controller;

import com.example.copelandactivemq.model.TopicName;
import com.example.copelandactivemq.service.PublisherService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PublisherController {

    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping(path = "/publish", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Send a message to subscribers. You will need the private key")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<String> publishToTopic(@RequestBody String inputMessage,
                                         @RequestParam("topic") TopicName topic,
                                         @RequestParam("key") String key) {

        return ResponseEntity.ok(publisherService.publishToTopic(inputMessage, topic, key));
    }
}
