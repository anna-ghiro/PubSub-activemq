package com.example.copelandactivemq.controller;

import com.example.copelandactivemq.model.ErrorMessageJson;
import com.example.copelandactivemq.model.TopicName;
import com.example.copelandactivemq.support.E2ETest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PublisherControllerTest extends E2ETest {

    @Test
    void publishToTopicIsSuccessful(){

        String result = endpointsSupport.publishToTopic(TopicName.FantasyNameTopic, "fantasyPrivateKey", "This was fun")
                .expectStatus().is2xxSuccessful()
                .returnResult(String.class)
                .getResponseBody().blockFirst();

        assertThat(result, equalTo("This was fun"));
    }

    @Test
    void unauthorizedPublishToTopicReturns401() {

        ErrorMessageJson errorMessage = endpointsSupport.publishToTopic(TopicName.CrimeNameTopic, "non-valid-key", "message")
                .expectStatus().isUnauthorized()
                .returnResult(ErrorMessageJson.class)
                .getResponseBody().blockFirst();

        assertThat(errorMessage.getMessage(), equalTo("You are not authorized"));
    }
}
