package org.example.other;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        int numberOfTasks = 3;
        CountDownLatch latch = new CountDownLatch(3);
        for (int i = 1; i <= numberOfTasks; i++) {
            Thread worker = new Thread(new Task(latch, i));
            worker.start();
        }
        latch.await();  // Главный поток ждет, пока счетчик не станет равным нулю
        System.out.println("Все задачи завершены. Главный поток продолжает выполнение.");
    }
}

class Task implements Runnable {
    private final CountDownLatch latch;
    private final int taskId;

    public Task(CountDownLatch latch, int taskId) {
        this.latch = latch;
        this.taskId = taskId;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long) (Math.random() * 2000));  // Имитация работы
        } catch (InterruptedException e) {} finally {
            latch.countDown();  // Уменьшаем счетчик
        }
    }
}