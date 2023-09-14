package com.example.copelandactivemq.support;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class E2ETest {

    @Autowired
    public WebTestClient webTestClient;

    public EndpointsSupport endpointsSupport;

    @BeforeEach
    public void setUp() {

        endpointsSupport = new EndpointsSupport(webTestClient);
    }
}
