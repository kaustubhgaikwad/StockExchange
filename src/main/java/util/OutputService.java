package util;

import model.Trade;

import java.util.List;
/**
 * OutputService enables to use different types of output methods
 * Currently CLI based
 */

public interface OutputService {
    public void print(List<Trade> registeredTrade);
}
