import model.InputParameters;
import model.Order;
import model.Trade;
import services.FIFOOrderMatchingRule;
import services.OrderManagementService;
import services.OrderMatchingRule;
import util.*;

import java.util.List;

public class Geektrust {
    public static void main(String[] args) {

        ParameterReader parameterReader = new ParameterReader(args);
        InputParameters parameters = parameterReader.processParameters();
        InputService inputReader = new FileReaderInput(parameters.getFileName());

        List<Order> orderList = inputReader.readInput();
        OrderMatchingRule matchingRule = new FIFOOrderMatchingRule();

        OrderManagementService stockExchangeService = new OrderManagementService(matchingRule);
        OutputService outputService = new CLIOutput();
        for (Order order : orderList) {
            List<Trade> registeredTrades = stockExchangeService.registerOrder(order);
            outputService.print(registeredTrades);
        }
    }
}
