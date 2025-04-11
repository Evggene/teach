package org.example.sprint1.workshop_1;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class WSh_1 {
    public static void main(String[] args) throws InterruptedException {
        var temperatures = generateTemperatures();

        //Время затрачено при виртуальных потоках - 6527 мс
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var timeSpentOnVirtualThreadsMs = handleTemperatures(temperatures, executor);
            System.out.println("Время затрачено при виртуальных потоках - " + timeSpentOnVirtualThreadsMs + " мс");
        }

        //Время затрачено при обычных потоках - 15071 мс
//         try (ExecutorService executor = Executors.newCachedThreadPool()) {
//             var timeSpentOnPlatformThreadsMs = handleTemperatures(temperatures, executor);
//             System.out.println("Время затрачено при обычных потоках - " + timeSpentOnPlatformThreadsMs + " мс");
//         }
    }

    // Запуск обработки значений температур с использованием конкретного экзекутора
    private static long handleTemperatures(Collection<Double> temperatures, ExecutorService executor) throws InterruptedException {
        var startThreads = LocalDateTime.now();
        try (executor) {
            temperatures.forEach(temp -> executor.submit(() -> handleTemperature(temp)));
        }
        return startThreads.until(LocalDateTime.now(), ChronoUnit.MILLIS);
    }

    // Генерируем диапазон температур с шагом 0.1 от -273.1 до 5526.0 градусов.
    // Всего значений - 57992.
    // Каждое значение требуется обработать в отдельном потоке
    private static Collection<Double> generateTemperatures() {
        return IntStream.range(-2731, 55261)
                .asDoubleStream()
                .map(it -> it / 10)
                .boxed()
                .toList();
    }

    // Обрабатываем конкретное значение температуры - выводим описание
    // ВНИМАНИЕ: операция эмулирует блокировку потока на 5 секунд
    private static void handleTemperature(double temperature) {
        String prefixMessage = ("Температура " + temperature + " : ");
        if (temperature < -273.1) {
            throw new IllegalArgumentException(prefixMessage + "Не бывает такой низкой температуры");
        } else if (temperature == -273.1) {
            System.out.println(prefixMessage + "Самая низкая возможная температура");
        } else if (temperature < 0.0) {
            System.out.println(prefixMessage + "Вода в твердом состоянии");
        } else if (temperature < 100.0) {
            System.out.println(prefixMessage + "Вода в жидком состоянии");
        } else if (temperature < 1669.0) {
            System.out.println(prefixMessage + "Горячо, но титан еще не плавится");
        } else if (temperature < 5526.0) {
            System.out.println(prefixMessage + "Титан плавится, но пока холоднее чем на солнце");
        } else {
            System.out.println(prefixMessage + "Температура как на солнце или выше");
        }
        try {
            TimeUnit.SECONDS.sleep(5L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}