package org.example.sprint1.tests;

import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
(Ваша задача — создать расширение для JUnit 5, которое будет:
Инициализировать внешний ресурс перед запуском всех тестов, чтобы он был готов для использования.
Закрыть ресурс по завершении всех тестов, чтобы избежать утечек памяти и других проблем, связанных с незакрытыми соединениями.
Перед каждым тестом проверять доступность ресурса и, если он недоступен, отменять выполнение теста, предотвращая потенциальные ошибки и сбои.
Собрать и сохранить информацию о времени выполнения каждого теста для анализа производительности и выявления потенциальных проблем с производительностью.)
 */
@ExtendWith({
        ResourceInitializerExtension.class,
        ResourceAvailabilityChecker.class,
        TestExecutionTimingExtension.class})
class ResourceManagementTest {

    @Test
    void shouldPerformLongRunningTest() throws InterruptedException {
        Thread.sleep(500);
        System.out.println("Выполняется долгий тест.");
        assertTrue(true);
    }

    @Test
    void shouldPerformShortRunningTest() throws InterruptedException {
        Thread.sleep(300);
        System.out.println("Выполняется короткий тест.");
        assertTrue(true);
    }
}

class ResourceInitializerExtension implements BeforeAllCallback, AfterAllCallback {

    private ExternalResource resource;

    @Override
    public void beforeAll(ExtensionContext context) {
        resource = new ExternalResource();
        //Нужно открыть ресурс        
        System.out.println("Ресурс инициализирован.");
    }

    @Override
    public void afterAll(ExtensionContext context) {
        //Нужно закрыть ресурс        
        System.out.println("Ресурс освобождён.");
    }
}

class ResourceAvailabilityChecker implements BeforeTestExecutionCallback {

    private final ExternalResource resource = new ExternalResource();

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        if (!resource.isAvailable()) {
            throw new RuntimeException("Ресурс недоступен для теста: " + context.getDisplayName());
        }
        System.out.println("Ресурс доступен для теста: " + context.getDisplayName());
    }
}

class TestExecutionTimingExtension implements BeforeEachCallback, AfterEachCallback {

    private final Map<String, Long> testDurations = new HashMap<>();

    @Override
    public void beforeEach(ExtensionContext context) {
        System.out.println("Начало теста: " + context.getDisplayName());
        testDurations.put(context.getDisplayName(), System.currentTimeMillis());
    }

    @Override
    public void afterEach(ExtensionContext context) {
        long startTime = testDurations.get(context.getDisplayName());
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Тест завершён: " + context.getDisplayName() + ". Продолжительность: " + duration + " мс.");
    }
}


@Getter
class ExternalResource {

    private boolean available = true;

    public void open() {
        available = true;
        System.out.println("Внешний ресурс открыт.");
    }

    public void close() {
        available = false;
        System.out.println("Внешний ресурс закрыт.");
    }

}