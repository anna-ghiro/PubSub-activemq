package com.example.copelandactivemq.support;

import com.example.copelandactivemq.model.TopicName;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

public class EndpointsSupport {

    private final WebTestClient webTestClient;

    public EndpointsSupport(WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }

    public String subscribeToTopic(TopicName topic) {
        return webTestClient.get().uri("/subscribe?topic={topic}", topic).exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(String.class)
                .returnResult().getResponseBody();
    }

    public WebTestClient.ResponseSpec publishToTopic(TopicName topic, String key, String inputMessage) {
        return webTestClient.post().uri("/publish?topic={topic}&key={key}", topic, key)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(inputMessage).exchange();
    }
}
