package org.example.other;

import java.util.function.Supplier;

public class SafeNull {

    public static <T> T getOrNull(Supplier<T> s) {
        try {
            return s.get();
        } catch (NullPointerException e) {
            return null;
        }
    }
}
