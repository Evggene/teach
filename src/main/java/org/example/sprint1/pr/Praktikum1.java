package org.example.sprint1.pr;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

class Praktikum1 {
    public static void main(String[] args) {
        List<Integer> ints = List.of(1, 2, 3, 4, 5);

        findFirstElement(ints, x -> x % 2 == 0).ifPresent(System.out::println);
        findFirstElement(ints, x -> x > 10).ifPresent(System.out::println);
    }

    static Optional<Integer> findFirstElement(List<Integer> values, Predicate<Integer> tester) {
        return values.stream()
                .filter(tester)
                .findFirst();
    }
}
