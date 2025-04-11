package org.example.sprint1.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;

class User {
    private final String name;
    private final int age;

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public boolean isValidAge() {
        return age >= 18 && age <= 60;
    }

    public boolean hasValidName() {
        return name != null && !name.trim().isEmpty();
    }
}

public class UserProfileTest {

    @ParameterizedTest
    @MethodSource("provideUsers")
    void testUserProfile(User user) {
        Assertions.assertTrue(user.hasValidName(), "Имя не должно быть пустым или иметь значение null");
        Assertions.assertTrue(user.isValidAge(), "Возвраст должен быть от 18 до 60");
    }

    private static Stream<User> provideUsers() {
        return Stream.of(
                new User("Alice", 30),
                new User("Bob", 25)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"привет", "JUnit", "параметризованный"})
    void testStringIsNotEmpty(String input) {
        assertFalse(input.isEmpty(), "Строка не должна быть пустой");
    }

    @ParameterizedTest
    @CsvSource({
            "10, 20, 30",
            "0, 0, 0",
            "-10, 5, -5"
    })
    void testSum(int a, int b, int expected) {
        Assertions.assertEquals(expected, a + b);
    }
}