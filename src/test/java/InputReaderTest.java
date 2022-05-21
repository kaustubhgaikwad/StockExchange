import model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.FileReaderInput;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class InputReaderTest {
    private FileReaderInput inputService;

    @BeforeEach
    public void instantiateInputService() {
        inputService = new FileReaderInput("sampleFileLocation");
    }

    @Test
    public void readOrderTest() {
        String sampleOrderString = "#3 09:47 BAC buy 238.10 110";
        Order order = inputService.readOrderFromLine(sampleOrderString);

        assertEquals("#3",order.getId());
        assertEquals("09:47",order.getTime().toString());
        assertEquals("BAC",order.getStock().getName());
        assertEquals("buy",order.getOrderType().name().toLowerCase());
        assertEquals(new BigDecimal("238.10"),order.getPrice());
        assertEquals(110,order.getQuantity());

    }
}
