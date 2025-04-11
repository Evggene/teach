package migration;

import lombok.extern.slf4j.Slf4j;
import org.example.sprint1.migration.OrderService;
import org.example.sprint1.migration.PaymentService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.migrationsupport.rules.EnableRuleMigrationSupport;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@EnableRuleMigrationSupport
@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceTest.class);

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private OrderService orderService;

    @BeforeClass
    public static void setUpBeforeClass() {
        logger.info(() -> "Запускаем все тесты для проверки сервиса заказов");
    }

    @AfterClass
    public static void tearDownAfterClass() {
        logger.info(() -> "Все тесты для проверки сервиса заказов были запущены");
    }

    @Test
    public void testOrderCreation() {
        // Настраиваем mock для успешной обработки платежа
        when(paymentService.processPayment(anyDouble())).thenReturn(true);

        // Проверяем создание заказа
        boolean result = orderService.createOrder(100.0);
        assertTrue(result);

        // Проверяем, что метод processPayment вызывался один раз
        verify(paymentService, times(1)).processPayment(100.0);
    }

    @Test
    @Ignore("Тест пропущен, требуется исправление логики отмены заказа")
    public void testOrderCancellation() {
        // Тест игнорируется, так как логика отмены требует исправления
        boolean result = orderService.cancelOrder(123);
        assertTrue(result);
    }

    @Test
    @Category(ProcessingTest.class)
    public void testOrderProcessing() {
        // Проверяем, что заказ может быть обработан
        boolean result = orderService.processOrder(123);
        assertTrue(result);
    }

    interface ProcessingTest {
    }
}
