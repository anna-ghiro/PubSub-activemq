package com.example.copelandactivemq.controller;

import com.example.copelandactivemq.model.TopicName;
import com.example.copelandactivemq.support.E2ETest;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SubscribeControllerTest extends E2ETest {

    @Test
    void subscribeToTopicIsSuccessful() {
        String result = endpointsSupport.subscribeToTopic(TopicName.ActionNameTopic);

        assertThat(result, equalTo("actionPrivateKey"));
    }
}
