package org.example.sprint1.tests;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ConditionalStabilityTest {

    @RepeatedTest(value = 5, name = RepeatedTest.LONG_DISPLAY_NAME)
    void testConditionalStability(RepetitionInfo repetitionInfo) {
        boolean result = runOperation();
        System.out.println("Запуск теста " + repetitionInfo.getCurrentRepetition() +
                " из " + repetitionInfo.getTotalRepetitions());

        if (repetitionInfo.getCurrentRepetition() == 5) {
            // Строгая проверка на последнем повторении            
            assertTrue(result, "Ожидается успешный результат на последнем повторении.");
        } else {
            // Мягкая проверка на всех остальных повторениях            
            assertFalse(result, "На этом повторении допустим произвольный результат.");
        }
    }

    boolean runOperation() {
        return Math.random() > 0.5; // Успешный результат в 50% случаев    
    }
}