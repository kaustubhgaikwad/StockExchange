package util;

import model.Trade;

import java.util.List;

/**
 * OutputService responsible for handling console output
 */

public class CLIOutput implements OutputService {

    @Override
    public void print(List<Trade> registeredTrades) {
        for (Trade trade : registeredTrades) {
            System.out.println(trade.toString());
        }
    }
}
