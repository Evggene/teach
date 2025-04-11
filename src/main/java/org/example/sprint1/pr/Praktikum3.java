package org.example.sprint1.pr;

class Praktikum3 {
    public static void main(String[] args) {
        System.out.println(extractDomain("user@somemail.com")); // somemail.com
        System.out.println(extractDomain("user@yandex.ru")); // yandex.ru
        System.out.println(extractDomain("@somemail.ru")); // yandex.ru
        System.out.println(extractDomain("not email")); // yandex.ru
        System.out.println(extractDomain("")); // yandex.ru
    }

    static String extractDomain(String email) {
        if (email == null) {
            return "yandex.ru";
        }
        var ind = email.indexOf('@');
        if (ind == -1) {
            return "yandex.ru";
        }
        return email.substring(ind);
    }
}
