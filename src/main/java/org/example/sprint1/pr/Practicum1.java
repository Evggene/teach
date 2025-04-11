package org.example.sprint1.pr;

import lombok.Getter;

import java.util.function.Function;
import java.util.function.Predicate;

class Practicum1 {

    enum Position {
        DIRECTOR, MANAGER, DEVELOPER
    }

    @Getter
    static class Employee {
        private final Position position;
        private final String name;
        private final int experienceYears;
        private final String department;

        public Employee(Position position, String name,
                        int experienceYears, String department) {
            this.position = position;
            this.name = name;
            this.experienceYears = experienceYears;
            this.department = department;
        }

    }

    public static Predicate<Employee> createNewDeveloperPredicate(String department) {
        return
                ((Predicate<Employee>) x -> x.position == Position.DEVELOPER)
                .and(x -> x.department.equalsIgnoreCase(department))
                .and(x -> 2025 - x.experienceYears < 1);
    }

    public static void main(String[] args) {
        var test1 = new Employee(Position.DEVELOPER, "test", 2025, "d");
        var p = createNewDeveloperPredicate("d");
        System.out.println(p.test(test1));

        Function<Integer, Integer> intFunction = it -> it;
        int result = intFunction
                .andThen(it -> it + 1)
                .andThen(it -> it * 2)
                .andThen(it -> it + 10)
                .apply(0);

        System.out.println(result);
    }

}
