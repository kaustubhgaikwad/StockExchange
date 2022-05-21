package util;

import com.google.common.base.Splitter;
import model.Order;
import model.OrderType;
import model.Stock;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * FileReaderInput handles reading input from file
 */

public class FileReaderInput implements InputService {

    private final String filename;

    public FileReaderInput(String filename) {
        this.filename = filename;
    }

    @Override
    public List<Order> readInput() {
        File file = new File(filename);
        List<Order> ordersList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                Order order = readOrderFromLine(sc.nextLine());
                ordersList.add(order);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    public Order readOrderFromLine(String inputLine) {
        Splitter spaceSplitter = Splitter.on(' ').omitEmptyStrings().trimResults();
        Iterator<String> tokenItr = spaceSplitter.split(inputLine).iterator();

        String id;
        LocalTime ordertime;
        Stock stock;
        OrderType orderType;
        BigDecimal price;
        int quantity;
        id = tokenItr.next();
        String time = tokenItr.next();
        ordertime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault()));
        String stockName = tokenItr.next();
        stock = new Stock(stockName);
        orderType = OrderType.valueOf(tokenItr.next().toUpperCase());
        price = new BigDecimal(tokenItr.next());
        quantity = Integer.parseInt(tokenItr.next());
        return new Order(id, ordertime, stock, orderType, price, quantity);
    }
}
