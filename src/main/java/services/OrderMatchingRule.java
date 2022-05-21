package services;

import model.Order;
import model.Stock;
import model.Trade;

import java.util.List;
import java.util.Map;

public interface OrderMatchingRule {
    public List<Trade> matchOrder(Map<Stock, List<Order>> sellOrder, Map<Stock, List<Order>> buyOrder);
}
