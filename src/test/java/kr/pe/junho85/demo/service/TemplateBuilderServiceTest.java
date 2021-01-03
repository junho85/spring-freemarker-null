package kr.pe.junho85.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TemplateBuilderServiceTest {
    @Autowired
    private TemplateBuilderService templateBuilderService;

    @Test
    void test() {
        // Given
        final String templateString = "Hello ${user}";
        Map<String, String> model = Map.of("user", "world");

        // When
        String result = templateBuilderService.build(templateString, model);

        // Then
        then(result)
                .isEqualTo("Hello world");

    }

}