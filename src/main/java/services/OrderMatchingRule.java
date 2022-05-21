package services;

import model.Order;
import model.Stock;
import model.Trade;

import java.util.List;
import java.util.Map;

/**
 * OrderMatchingRule Interface
 * This interface will be implemented all different types of rule
 * Currently only have FirstInFirstOut Price-Time order-matching rule
 */

public interface OrderMatchingRule {
    public List<Trade> matchOrder(Map<Stock, List<Order>> sellOrder, Map<Stock, List<Order>> buyOrder);
}
