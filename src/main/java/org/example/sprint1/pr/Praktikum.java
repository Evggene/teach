package org.example.sprint1.pr;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.StructuredTaskScope;

class Praktikum {
    public static void main(String[] args) {
//        Thread.startVirtualThread(() -> System.out.println("it is virtual"));
//        Thread.ofVirtual().name("name").start(() -> System.out.println("ss"));
//        try (var virtualExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
//            virtualExecutor.submit(() -> System.out.println("Я выполняюсь в первом виртуальном потоке"));
//            virtualExecutor.submit(() -> System.out.println("А я во втором"));
//            virtualExecutor.submit(() -> System.out.println("И между нами переключаются Carrier Thread"));
//        }

        var start = LocalDateTime.now();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var random = new Random();
            for (int i = 0; i < 100_000; i++) {
                int taskId = i;
                executor.submit(() -> {
                    long delay = 500 + random.nextInt(1500);  // имитация задержки
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException _) {}
                    System.out.printf("Задача #%d завершена за %d мс%n", taskId, delay);
                });
            }}
        System.out.println("Все задачи выполнены за " + start.until(LocalDateTime.now(), ChronoUnit.SECONDS));

        ScopedValue<String> GROUP_NAME = ScopedValue.newInstance();
        // Создаём скоуп, внутри которого будет доступно заданное значение "Группа 1"
        ScopedValue.where(GROUP_NAME, "Группа 1").run(() -> {
            // Выполняется не в отдельном потоке, значение доступно
            System.out.println("Значение - " + GROUP_NAME.get());
            // Создадим внутри блок structured concurrency,
            // внутри которого значение будет доступно и подзадачам
            try (var scope = new StructuredTaskScope<>()) {
                var result1 = scope.fork(() -> "Мне доступно - " + GROUP_NAME.get());
                var result2 = scope.fork(() -> "Мне тоже - " + GROUP_NAME.get());
                scope.join();
                System.out.println(result1.get() + result2.get());
            } catch (Exception e) {
            }
        });
    }

}
