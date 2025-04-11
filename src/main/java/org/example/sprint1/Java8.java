package org.example.sprint1;

import lombok.Getter;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Java8 {

    public static void main(String[] args) {
        IntPredicate isEven = x -> x % 2 == 0;
        Comparator<Person> ageComparator = (a, b) -> {
            return a.getAge() - b.getAge();
        };
        Function<String, Integer> parser = Integer::parseInt;

        int x = parser.apply("3");
        int y = parser.apply("5");
        System.out.println(x + y);

        Person p1 = new Person("Иван", 20);
        Person p2 = new Person("Василий", 35);
        System.out.println(ageComparator.compare(p1, p2)); // < 0
        System.out.println(ageComparator.compare(p2, p1)); // > 0
        System.out.println(ageComparator.compare(p1, p1)); // == 0

        System.out.println(isEven.test(2));
        System.out.println(isEven.test(3));

        printAllItems(List.of(1,2,3));
    }

    interface IntPredicate {
        boolean test(int value);
    }

    @Getter
    static class Person {
        private final String name;
        private final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name + ", age = " + age;
        }
    }

    static <T> void printAllItems(Collection<T> items) {
        items.forEach(System.out::println);
    }
}