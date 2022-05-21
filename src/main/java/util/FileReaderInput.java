package util;

import model.Order;
import model.OrderType;
import model.Stock;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

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
        String id;
        LocalTime ordertime;
        Stock stock;
        OrderType orderType;
        BigDecimal price;
        int quantity;
        String[] orderDetails = inputLine.split(" ");
        id = orderDetails[0];
        String time = orderDetails[1];
        ordertime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault()));
        String stockName = orderDetails[2];
        stock = new Stock(stockName);
        orderType = OrderType.valueOf(orderDetails[3].toUpperCase());
        price = new BigDecimal(orderDetails[4]);
        quantity = Integer.parseInt(orderDetails[5]);
        return new Order(id, ordertime, stock, orderType, price, quantity);
    }
}
