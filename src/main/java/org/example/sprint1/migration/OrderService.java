package org.example.sprint1.migration;

public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public boolean createOrder(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма заказа должна быть положительной");
        }
        return paymentService.processPayment(amount);
    }

    public boolean cancelOrder(int orderId) {
        return true; // Предполагаем, что заказ всегда отменен успешно
    }

    public boolean processOrder(int orderId) {
        return true; // Предполагаем, что заказ всегда обработан успешно
    }
}
