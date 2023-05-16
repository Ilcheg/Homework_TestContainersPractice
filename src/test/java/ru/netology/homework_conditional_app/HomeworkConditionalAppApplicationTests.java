package ru.netology.homework_conditional_app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import ru.netology.homework_conditional_app.profile.DevProfile;
import ru.netology.homework_conditional_app.profile.ProductionProfile;
import ru.netology.homework_conditional_app.profile.SystemProfile;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HomeworkConditionalAppApplicationTests {
    private static final GenericContainer<?> devApp = new GenericContainer<>("devapp")
            .withExposedPorts(8080);
    private static final GenericContainer<?> prodApp = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);
    @Autowired
    TestRestTemplate restTemplate;

    @BeforeAll
    public static void setUp() {
        devApp.start();
        prodApp.start();
    }

    @Test
    void contextLoadsDevApp() {
        Integer devAppPort = devApp.getMappedPort(8080);
        ResponseEntity<String> entityFromDevApp = restTemplate.getForEntity("http://localhost:" + devAppPort + "/profile", String.class);
        Assertions.assertEquals(entityFromDevApp.getBody(), new DevProfile().getProfile());
        System.out.println(entityFromDevApp.getBody());
    }

    @Test
    void contextLoadsProdApp() {
        Integer prodAppPort = prodApp.getMappedPort(8081);
        ResponseEntity<String> entityFromProdAPP = restTemplate.getForEntity("http://localhost:" + prodAppPort + "/profile", String.class);
        Assertions.assertEquals(entityFromProdAPP.getBody(), new ProductionProfile().getProfile());
        System.out.println(entityFromProdAPP.getBody());
    }
}
