package org.example.sprint1.pr;

public class Praktikum5 {
    //Задание
    //Напишите метод getSeason, который принимает номер месяца и возвращает строку с названием времени года.
    // Используйте switch-выражение с yield для обработки случаев, когда месяц не входит в диапазон от 1 до 12.
    public static void main(String[] args) {
        System.out.println(getSeason(13));
        System.out.println(getSeason(-1));
        System.out.println(getSeason(2));
    }

    public static String getSeason(int month) {
        return switch (month) {
            case 12, 1, 2 -> "Зима";
            case 3, 4, 5 -> "Весна";
            case 6, 7, 8 -> "Лето";
            case 9, 10, 11 -> "Осень";
            default -> {
                var message = "Ошибка: некорректный номер месяца. ";
                if (month < 1) {
                    message += "Значение не может быть меньше 1. ";
                } else {
                    message += "Значение не может быть больше 12. ";
                }
                yield message + "Допустимые значения - [1..12]";
            }
        };
    }
}
