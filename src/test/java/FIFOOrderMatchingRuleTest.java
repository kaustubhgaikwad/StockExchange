import model.Order;
import model.OrderType;
import model.Stock;
import model.Trade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import services.FIFOOrderMatchingRule;
import services.OrderMatchingRule;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FIFOOrderMatchingRuleTest {
    private Map<Stock, List<Order>> buyOrders;
    private Map<Stock, List<Order>> sellOrders;
    private OrderMatchingRule rule;

    @BeforeEach
    void instantiateTest() {
        this.rule = new FIFOOrderMatchingRule();
        buyOrders = new HashMap<>();
        sellOrders = new HashMap<>();
    }

    @Test
    void matchOrderTest() {
        Order sellOrder1 = new Order("#1", LocalTime.NOON,new Stock("ABC"), OrderType.SELL,new BigDecimal("100"),12);
        Order buyOrder1  = new Order("#2", LocalTime.NOON,new Stock("ABC"), OrderType.BUY,new BigDecimal("98"),10);
        Order buyOrder2  = new Order("#3", LocalTime.NOON,new Stock("ABC"), OrderType.BUY,new BigDecimal("110"),10);

        List<Order> sellOrderList = new ArrayList<>();
        sellOrderList.add(sellOrder1);
        sellOrders.put(new Stock("ABC"),sellOrderList);

        List<Order> buyOrderList = new ArrayList<>();
        buyOrderList.add(buyOrder1);
        buyOrders.put(new Stock("ABC"),buyOrderList);

        List<Trade> matchedTrades1 = rule.matchOrder(sellOrders, buyOrders);
        List<Order> orderList = buyOrders.get(new Stock("ABC"));
        orderList.add(buyOrder2);

        List<Trade> matchedTrades2 = rule.matchOrder(sellOrders, buyOrders);

        assertEquals(matchedTrades1.size(), 0);
        assertEquals(matchedTrades2.get(0).getBuyOrderId(), "#3");
        assertEquals(matchedTrades2.get(0).getSellPrice(), new BigDecimal("100"));
        assertEquals(matchedTrades2.get(0).getQuantity(), 10);
        assertEquals(matchedTrades2.get(0).getSellOrderId(), "#1");



    }
}
