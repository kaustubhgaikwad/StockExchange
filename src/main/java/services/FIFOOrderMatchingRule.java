package services;

import model.Order;
import model.Stock;
import model.Trade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * FirstInFirstOut Price-Time order-matching rule implementing OrderMatchingRule
 */

public class FIFOOrderMatchingRule implements OrderMatchingRule {
    @Override
    public List<Trade> matchOrder(Map<Stock, List<Order>> sellOrders, Map<Stock, List<Order>> buyOrders) {
        List<Trade> registeredTrades = new ArrayList<>();
        if (buyOrders.isEmpty() || sellOrders.isEmpty())
            return registeredTrades;
        buyOrders.forEach((stock, order) -> {

            for (Order buyOrder : order) {
                List<Order> sellOrdersForStock = sellOrders.getOrDefault(stock, null);
                List<Order> matchedOrders = new ArrayList<>();
                BigDecimal buyOrderPrice = buyOrder.getPrice();
                int remainingQuantity = buyOrder.getQuantity();
                if (sellOrdersForStock == null)
                    break;
                for (Order sellOrderForStock : sellOrdersForStock) {
                    if (remainingQuantity == 0) break;
                    if (sellOrderForStock.getQuantity() == 0) continue;
                    if (buyOrderPrice.compareTo(sellOrderForStock.getPrice()) >= 0) {

                        matchedOrders.add(sellOrderForStock);

                        if (sellOrderForStock.getQuantity() > remainingQuantity) {
                            remainingQuantity = 0;
                        } else {
                            remainingQuantity = remainingQuantity - sellOrderForStock.getQuantity();
                        }
                    }
                }
                remainingQuantity = buyOrder.getQuantity();
                for (Order matchedOrder : matchedOrders) {
                    if (remainingQuantity > matchedOrder.getQuantity()) {
                        Trade trade = new Trade(buyOrder.getId(), matchedOrder.getPrice(), matchedOrder.getQuantity(), matchedOrder.getId());
                        remainingQuantity = remainingQuantity - matchedOrder.getQuantity();
                        matchedOrder.setQuantity(0);
                        registeredTrades.add(trade);
                    } else {
                        Trade trade = new Trade(buyOrder.getId(), matchedOrder.getPrice(), remainingQuantity, matchedOrder.getId());
                        int qty = matchedOrder.getQuantity() - remainingQuantity;
                        matchedOrder.setQuantity(qty);
                        registeredTrades.add(trade);
                        remainingQuantity = 0;
                    }
                }
                buyOrder.setQuantity(remainingQuantity);
            }


        });
        return registeredTrades;
    }
}
