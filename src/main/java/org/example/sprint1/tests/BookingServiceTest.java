package org.example.sprint1.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
Задание:
Основной уровень
Настройте BookingService, добавив инициализацию и завершение работы сервиса в методах initializeService и logOnServiceCleanUp.
Вложенные классы и логика:
В классе WhenBookingIsCreated добавьте логику для создания объекта Booking, настроив его в initializeBooking, настройте завершение работы с этим объектом в методе logOnTheCleanup.
В WhenBookingIsConfirmed обеспечьте инициализацию состояния бронирования в методе confirmBooking и настройте завершение в cleanupConfirmedBooking.
Методы тестов
Добавьте тестовые методы, которые проверят корректность состояния бронирования на каждом этапе: для создания, подтверждения и отмены бронирования.
 */
class BookingServiceTest {

    private BookingService bookingService;
    private Booking booking;

    @BeforeEach
    void initializeService() {
        bookingService = new BookingService();
        System.out.println("BookingService инициализирован.");
    }

    @AfterEach
    void logOnServiceCleanUp() {
        System.out.println("BookingService завершил работу.");
    }

    @Nested
    class WhenBookingIsCreated {

        @BeforeEach
        void initializeBooking() {
            booking = bookingService.createBooking();
            System.out.println("Бронирование создано с начальным статусом.");
        }

        @AfterEach
        void logOnTheCleanup() {
            System.out.println("Завершена работа с созданным бронированием.");
        }

        @Test
        void shouldHaveCreatedStatus() {
            assertEquals("CREATED", booking.getStatus());
        }

        @Test
        void shouldRemainCreatedStatusAfterEdit() {
            bookingService.editBooking(booking);
            assertEquals("CREATED", booking.getStatus());
        }
    }

    @Nested
    class WhenBookingIsConfirmed {

        @BeforeEach
        void confirmBooking() {
            booking = bookingService.createBooking();
            bookingService.confirmBooking(booking);
            System.out.println("Бронирование подтверждено.");
        }

        @AfterEach
        void cleanupConfirmedBooking() {
            System.out.println("Завершена работа с подтвержденным бронированием.");
        }

        @Test
        void shouldHaveConfirmedStatus() {
            assertEquals("CONFIRMED", booking.getStatus());
        }

        @Test
        void shouldChangeStatusToCanceledAfterCancellation() {
            bookingService.cancelBooking(booking);
            assertEquals("CANCELED", booking.getStatus());
        }
    }
}

class BookingService {

    public Booking createBooking() {
        return new Booking("CREATED");
    }

    public void editBooking(Booking booking) {
        System.out.println("Обновление бронирования: " + booking);
    }

    public void confirmBooking(Booking booking) {
        booking.setStatus("CONFIRMED");
    }

    public void cancelBooking(Booking booking) {
        booking.setStatus("CANCELED");
    }
}

class Booking {
    private String status;

    public Booking(String status) {
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}