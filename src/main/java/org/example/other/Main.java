package org.example.other;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        var m = new Main();
        m.a((_) -> new A().a());
        m.b(A::new);
        m.c(((_) -> new A()));
        m.d(_ -> new B().b());

        var c1 = new C(null, new C(null, null));
        var c2 = new C(null, new C(null, new C("hello", null)));
        var c3 = new C(null, null);

        System.out.println(SafeNull.getOrNull(() -> c1.getC().getString()));
        System.out.println(SafeNull.getOrNull(() -> c2.getC().getC().getString()));
        System.out.println(SafeNull.getOrNull(() -> c3.getC().getC().getString()));

        var s = SafeNull.getOrNull(() -> c2.getC().getC().getString());
        System.out.println(s);

        Stream.iterate(0, x -> x * 3 + 1).limit(5).toList().forEach(System.out::println);

        IntStream.rangeClosed(0, 100)
                .filter(x -> x % 2 == 0)
                .filter(x -> x % 7 == 0)
                .filter(x -> x > 30)
                .forEach(it -> System.out.print(it + " "));

        System.out.println();
        Stream.of(1, 1, 8, 5, 2, 9, 7, 7, 2, 9, 8, 9, 8, 6, 9)
                .distinct()
                .sorted((a,b) -> Integer.compare(b, a))
                .forEach(it -> System.out.print(it + " "));

        System.out.println();
        List<String> letters = List.of("H", "e", "l", "l", "o", ",", " ", "w", "o", "r", "l", "d");
        String result = letters.stream()
                .reduce("", (x, y) -> x + y );

        System.out.println(result);
    }

    void a(Consumer<Main> c) {
        c.accept(this);
    }

    void b(Supplier<A> s) {
        s.get().a();
    }

    void c(Function<Main, A> f) {
        f.apply(this).a();
    }

    // замена тела обратным вызовом
    void d(Consumer<Object> c) {
        try {
            c.accept(this);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }



}
