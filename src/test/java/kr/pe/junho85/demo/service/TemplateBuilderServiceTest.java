package kr.pe.junho85.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
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

    @Test
    void test2() {
        // Given
        final String templateString = "Hello ${user}";
        Map<String, String> model = Map.of("user", "june");

        // When
        String result = templateBuilderService.build(templateString, model);

        // Then
        then(result)
                .isEqualTo("Hello june");
    }

    @Test
    void test3() {
        // Given
        final String templateString = "Hello ${user!}";
        Map<String, String> model = Map.of();

        // When
        String result = templateBuilderService.build(templateString, model);

        // Then
        then(result)
                .isEqualTo("Hello ");
    }

    @Test
    void test4() {
        // Given
        final String templateString = "Hello ${user!\"member\"}";
        Map<String, String> model = Map.of();

        // When
        String result = templateBuilderService.build(templateString, model);

        // Then
        then(result)
                .isEqualTo("Hello member");
    }

    @Test
    void test_date() {
        // Given
        final String templateString = "지금은 ${date?string('yyyy-MM-dd a hh:mm:ss')}";
        Map<String, Object> model = Map.of("date", new Date());

        // When
        String result = templateBuilderService.build(templateString, model);

        // Then
        System.out.println(result); // e.g. 지금은 2021-01-03 오후 05:01:56
    }

    @Test
    void test_date_not_exists() {
        // Given
        final String templateString = "지금은 ${(date?string('yyyy-MM-dd a hh:mm:ss'))!}";
        Map<String, Object> model = Map.of();

        // When
        String result = templateBuilderService.build(templateString, model);

        // Then
        System.out.println(result); // e.g. 지금은
    }

    @Test
    void test_date_null() {
        // Given
        final String templateString = "지금은 ${(date?string('yyyy-MM-dd a hh:mm:ss'))!}";
        Map<String, Object> model = new HashMap<>() {{
            put("date", null);
        }};

        // When
        String result = templateBuilderService.build(templateString, model);

        // Then
        System.out.println(result); // e.g. 지금은
    }

}