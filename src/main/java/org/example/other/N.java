package org.example.other;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class N {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var future = CompletableFuture.supplyAsync(() -> "Hello");
        var future2 = CompletableFuture.supplyAsync(() -> "!");
        var resultFuture = future
                .thenApply(result -> result + ", World")
                .thenCombine(future2, (result1, result2) -> result1 + result2)
                .exceptionally(ex -> "Handled: " + ex.getMessage());
        String result = resultFuture.get();
        System.out.println(result);
    }
}
