import model.Order;
import model.OrderType;
import model.Stock;
import model.Trade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.FIFOOrderMatchingRule;
import services.OrderManagementService;
import services.OrderMatchingRule;
import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

public class OrderManagementServiceTest {
    private OrderManagementService orderManagementService;

    @BeforeEach
    void instantiateTest() {
        OrderMatchingRule rule = new FIFOOrderMatchingRule();
        orderManagementService = new OrderManagementService(rule);
    }

    @Test
    void addOrderTest() {
        Order sellOrder1 = new Order("#1", LocalTime.NOON,new Stock("ABC"), OrderType.SELL,new BigDecimal("100"),10);
        Order sellOrder2 = new Order("#2", LocalTime.NOON,new Stock("DEF"), OrderType.SELL,new BigDecimal("110"),10);
        Order buyOrder1  = new Order("#3", LocalTime.NOON,new Stock("XYZ"), OrderType.BUY,new BigDecimal("100"),10);

        orderManagementService.addOrder(sellOrder1);
        orderManagementService.addOrder(sellOrder2);
        orderManagementService.addOrder(buyOrder1);
        List<Order> sellOrder1Test = orderManagementService.getSellOrders().get(new Stock("ABC"));
        List<Order> buyOrder1Test = orderManagementService.getBuyOrders().get(new Stock("XYZ"));

        assertEquals(sellOrder1Test.size(), 1);
        assertEquals(sellOrder1Test.get(0).getId(), "#1");

        assertEquals(buyOrder1Test.size(), 1);
        assertEquals(buyOrder1Test.get(0).getId(), "#3");

    }

    @Test
    void registerOrderTest() {
        Order sellOrder1 = new Order("#1", LocalTime.NOON,new Stock("ABC"), OrderType.SELL,new BigDecimal("100"),10);
        Order sellOrder2 = new Order("#2", LocalTime.NOON,new Stock("DEF"), OrderType.SELL,new BigDecimal("110"),10);
        Order buyOrder1  = new Order("#3", LocalTime.NOON,new Stock("ABC"), OrderType.BUY,new BigDecimal("100"),10);

        List<Trade> trade1 = orderManagementService.registerOrder(sellOrder1);
        List<Trade> trade2 = orderManagementService.registerOrder(sellOrder2);
        List<Trade> trade3 = orderManagementService.registerOrder(buyOrder1);

        assertEquals(trade1.size(), 0);
        assertEquals(trade2.size(), 0);
        assertEquals(trade3.size(), 1);
        assertEquals(trade3.get(0).getSellOrderId(), "#1");
    }


}
