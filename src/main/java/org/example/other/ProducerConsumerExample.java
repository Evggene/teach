package org.example.other;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerExample {
    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();  // Условие "буфер не пуст"
    private Integer buffer = null;  // Буфер для одного элемента

    // Производитель добавляет данные в буфер
    public void produce(int value) throws InterruptedException {
        lock.lock();
        try {
            while (buffer != null) {
                System.out.println("Производитель ждет, буфер полон.");
                notEmpty.await();  // Ожидаем, пока буфер не освободится
            }
            buffer = value;
            System.out.println("Производитель добавил: " + value);
            notEmpty.signal();  // Уведомляем потребителя, что буфер не пуст
        } finally {
            lock.unlock();
        }
    }

    // Потребитель извлекает данные из буфера
    public int consume() throws InterruptedException {
        lock.lock();
        try {
            while (buffer == null) {
                System.out.println("Потребитель ждет, буфер пуст.");
                notEmpty.await();  // Ожидаем, пока в буфере не появятся данные
            }
            int value = buffer;
            buffer = null;
            System.out.println("Потребитель извлек: " + value);
            notEmpty.signal();  // Уведомляем производителя, что буфер пуст
            return value;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ProducerConsumerExample example = new ProducerConsumerExample();

        // Поток-производитель
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    example.produce(i);
                    Thread.sleep(500);  // Имитация работы
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Поток-потребитель
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    example.consume();
                    Thread.sleep(1000);  // Имитация работы
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}