package util;

import model.Order;

import java.util.List;

/**
 * InputService enables to use different types of input methods
 * Currently File based
 */
public interface InputService {
    public List<Order> readInput();
}
