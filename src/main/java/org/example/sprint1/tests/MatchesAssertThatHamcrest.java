package org.example.sprint1.tests;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class MatchesAssertThatHamcrest {
    @Test
    void testEvenNumbersInList() {
        List<Integer> numbers = Arrays.asList(2, 4, 6, 8);
        assertThat(numbers, EvenNumbersMatcher.containsOnlyEvenNumbers());
    }
}

class EvenNumbersMatcher extends TypeSafeMatcher<List<Integer>> {

    public static EvenNumbersMatcher containsOnlyEvenNumbers() {
        return new EvenNumbersMatcher();
    }

    @Override
    protected boolean matchesSafely(List<Integer> list) {
        return list.stream().allMatch(num -> num % 2 == 0);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("список должен содержать только чётные числа");
    }

    // Описывает, в чём конкретно состояло несоответствие при провале проверки
    @Override
    protected void describeMismatchSafely(List<Integer> list, Description mismatchDescription) {
        mismatchDescription.appendText("список содержит нечётные числа");
    }
}
