package org.example.authorizationapplication2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
public class AuthorizationAppContainerTest {

    static GenericContainer<?> devApp = new GenericContainer<>("authorizationapp:latest")
            .withExposedPorts(8080)
            .withEnv("SPRING_PROFILES_ACTIVE", "dev");

    static GenericContainer<?> prodApp = new GenericContainer<>("authorizationapp:latest")
            .withExposedPorts(8081)
            .withEnv("SPRING_PROFILES_ACTIVE", "prod");

    static TestRestTemplate restTemplate = new TestRestTemplate();

    @BeforeAll
    public static void setUp() {
        devApp.start();
        prodApp.start();
    }

    @Test
    void devAppShouldRespondOnSignin() {
        int port = devApp.getMappedPort(8080);
        String body = restTemplate.getForObject("http://localhost:" + port + "/signin", String.class);
        assertTrue(body.contains("Login") || body.contains("Вход"));
    }

    @Test
    void prodAppShouldRespondOnSignin() {
        int port = prodApp.getMappedPort(8081);
        String body = restTemplate.getForObject("http://localhost:" + port + "/signin", String.class);
        assertTrue(body.contains("Login") || body.contains("Вход"));
    }
}
