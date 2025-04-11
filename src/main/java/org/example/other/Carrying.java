package org.example.other;

import java.util.function.Function;

public class Carrying {

    public static void main(String[] args) {
        Function<Integer, Function<Integer, Integer>> curryMulti = u -> v -> u * v;
        System.out.println("Multiply 2, 3 :"
                + curryMulti
                .apply(2)
                .apply(3));
    }
}
