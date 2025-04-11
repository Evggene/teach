package org.example.other;

import io.vavr.control.Either;

import java.util.Optional;

public class EitherEx {

    public static void main(String[] args) {
        System.out.println(parseInt2("q").orElse(2));
        parseInt2("10").ifPresent(System.out::println);

        System.out.println(parseInt("q").getLeft());
        System.out.println(parseInt("5").get());
        System.out.println(parseInt("qq").getOrElse(0));

    }

    static Either<String, Integer> parseInt(String i) {
        try {
            return Either.right(Integer.parseInt(i));
        } catch (Exception e) {
            return Either.left("ошибка");
        }
    }

    static Optional<Integer> parseInt2(String i) {
        try {
            return Optional.of(Integer.parseInt(i));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
