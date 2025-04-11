package org.example.sprint1.workshop_1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.SequencedCollection;
import java.util.stream.IntStream;

/**
 * Журнал для упорядоченной записи объектов в журнал
 *
 * @param <T> тип записываемого объекта (при тестировании используйте {@link String}, представим что это журнал логов)
 */

public class WSh_2<T> {

    // Для хранения и получения информации подберите подходящий тип коллекции
    private final SequencedCollection<T> entries = new LinkedList<>();

    /**
     * Добавить в журнал новую запись (запись добавляется в конец)
     *
     * @param entry запись в журнал
     */
    void logEntry(T entry) {
        entries.addLast(entry);
    }

    /**
     * Получить последние актуальные записи в журнале
     *
     * @param limit число записей для получения
     * @return последние записи в журнале, ограниченные limit
     */

    List<T> getLastEntries(int limit) {
        return entries.reversed().stream().limit(limit).toList();
    }

    public static void main(String[] args) {
        var journal = new WSh_2<String>();
        // Добавляем по очереди числа 1..99 а журнал

        IntStream.range(1, 100).boxed().map(Object::toString).forEach(journal::logEntry);
        // Последние десять записей должны быть - 90..99
        var expectedLastTenEntries = IntStream.range(90, 100).boxed().map(Object::toString).toList().reversed();

        // Получаем фактические 10 последних записей
        var actualLastTenEntries = journal.getLastEntries(10);

        // Проверяем соответствие последниъ 10 записей ожидаемым
        if (!actualLastTenEntries.equals(expectedLastTenEntries)) {
            throw new RuntimeException("Ой, записи не совпали. Ожидаемые - [90..99]. Фактические - " + journal.getLastEntries(10));
        } else System.out.println("Ура-ура-ура");
    }
}