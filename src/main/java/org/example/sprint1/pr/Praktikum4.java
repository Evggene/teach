package org.example.sprint1.pr;

class Praktikum4 {
    static class Book {
        private static Long idCounter = 0L;

        private Long id;
        private String name;
        private String author;

        public Book(String name, String author) {
            this.id = idCounter++;
            this.name = name;
            this.author = author;
        }

        @Override
        public String toString() {
            return "Book [" + id + "] (" + name + ", " + author + ")";
        }
    }

    public static void main(String[] args) throws Throwable {
        // с помощью MethodHandles создайте объект класса Book c произвольными аргументами
        // и выведите его в консоль
//        MethodHandles.Lookup lookup = MethodHandles.lookup();
//        MethodHandle constructor = lookup.findConstructor(Book.class, MethodType.methodType(void.class, String.class, String.class));
//        var book = ((Book)constructor.invokeExact("a", "b"));
//        System.out.println(book);

        var bookClass = Book.class;
        var constructor = bookClass.getConstructor(String.class, String.class);
        var book = constructor.newInstance("a", "b");
        System.out.println(book);
    }
}
