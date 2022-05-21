package services;

import model.Order;
import model.OrderType;
import model.Stock;
import model.Trade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OrderManagementSerivec
 * Responsible for handling all operations relared to buy and sell orders
 */
public class OrderManagementService {

    private Map<Stock, List<Order>> buyOrders;
    private Map<Stock, List<Order>> sellOrders;
    private OrderMatchingRule rule;

    public OrderManagementService(OrderMatchingRule rule) {
        this.rule = rule;
        this.buyOrders = new HashMap<>();
        this.sellOrders = new HashMap<>();
    }

    public Map<Stock, List<Order>> getBuyOrders() {
        return buyOrders;
    }

    public Map<Stock, List<Order>> getSellOrders() {
        return sellOrders;
    }

    public void addOrder(Order order) {

        if (order.getOrderType() == OrderType.BUY) {
            List<Order> orderList = buyOrders.getOrDefault(order.getStock(), null);
            if (orderList == null) {
                orderList = new ArrayList<>();
                buyOrders.put(order.getStock(), orderList);
            }
            orderList.add(order);
        } else if (order.getOrderType() == OrderType.SELL) {
            List<Order> orderList = sellOrders.getOrDefault(order.getStock(), null);
            if (orderList == null) {
                orderList = new ArrayList<>();
                sellOrders.put(order.getStock(), orderList);
            }
            orderList.add(order);
        }

    }


    public List<Trade> registerOrder(Order order) {

        addOrder(order);

        List<Trade> tradesRegistered = rule.matchOrder(sellOrders, buyOrders);
        cleanUpMatchedTrades();

        return tradesRegistered;
    }

    /**
     * Removed the orders which are already matched
     * [When buy/sell order's are matched it's quantity is set to 0 to mark them eligible for removal]
     */
    private void cleanUpMatchedTrades() {
        List<Order> matchedSellOrderList = new ArrayList<>();
        sellOrders.forEach((stock, order) -> {
            for (Order sellOrder : order) {
                if (sellOrder.getQuantity() == 0)
                    matchedSellOrderList.add(sellOrder);
            }
        });
        for (Order order : matchedSellOrderList) {
            sellOrders.remove(order);
        }

        List<Order> matchedBuyOrderList = new ArrayList<>();
        buyOrders.forEach((stock, order) -> {
            for (Order buyOrder : order) {
                if (buyOrder.getQuantity() == 0)
                    matchedBuyOrderList.add(buyOrder);
            }
        });
        for (Order order : matchedBuyOrderList) {
            buyOrders.remove(order);
        }

    }
}
