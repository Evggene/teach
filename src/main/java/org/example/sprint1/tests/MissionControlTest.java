package org.example.sprint1.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
Ваша задача:
Настроить выполнение одного из тестов только в тестовой среде.
Отключить выполнение другого теста для Windows.
Реализовать и применить пользовательское условие, проверяющее, что тест будет запускаться только по будним дням.
 */
public class MissionControlTest {

    @Test
    @EnabledIf("org.example.sprint1.MissionControlTest#isTestEnvironment")
    void testOnlyInTestEnv() {
        System.out.println("Тест выполняется только в тестовой среде.");
    }

    @Test
    @DisabledOnOs({OS.WINDOWS })
    void notOnLinux() {
        System.out.println("Этот тест не выполняется на Windows.");
    }

    @Test
    @ExtendWith(MissionTypeCondition.class)
        // Подключаем условие
    void missionSpecificTest() {
        System.out.println("Тест выполняется только для конкретной миссии.");
    }

    @Test
    @MissionTypeConditionI
    void missionSpecificTestI() {
        System.out.println("Тест выполняется только для конкретной миссии.");
    }

    private static boolean isTestEnvironment() {
        return "test".equals(System.getProperty("ENV"));
    }
}

class MissionTypeCondition implements ExecutionCondition {
    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        boolean isMissionDay = java.time.LocalDate.now().getDayOfWeek().getValue() <= 5; // Запуск только в будние дни
        return isMissionDay
                ? ConditionEvaluationResult.enabled("Тест включён для будних дней")
                : ConditionEvaluationResult.disabled("Тест отключён на выходные");
    }
}

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(MissionTypeCondition.class)  // Подключаем условие
@interface MissionTypeConditionI {
}
