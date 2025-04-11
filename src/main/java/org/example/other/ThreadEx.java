package org.example.other;

public class ThreadEx {
    public static void main(String[] args) throws InterruptedException {
        var badThread = new Thread(() -> {throw new UnsupportedOperationException();});
        badThread.setUncaughtExceptionHandler((thread, exception) -> {
            // обработка
        badThread.start();
    });
    }
}
