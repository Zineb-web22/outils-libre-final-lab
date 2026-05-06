package pricing;

import org.junit.jupiter.api.Test;
import pricing.model.CustomerType;
import pricing.model.PricingResult;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class PricingEngineTest {

    @Test
    public void testRegularCustomerNoDiscount() {
        PricingEngine engine = new PricingEngine();
        PricingResult result = engine.calculate(Arrays.asList(10.0, 20.0), Arrays.asList(1, 1), CustomerType.REGULAR, null);
        assertEquals(30.0, result.getSubtotal(), 0.01);
        assertEquals(36.0, result.getFinalPrice(), 0.01);
    }

    @Test
    public void testVIPCustomer() {
        PricingEngine engine = new PricingEngine();
        PricingResult result = engine.calculate(Arrays.asList(100.0), Arrays.asList(1), CustomerType.VIP, null);
        assertEquals(102.0, result.getFinalPrice(), 0.01);
    }

    @Test
    public void testRegularCustomerWithSave10() {
        PricingEngine engine = new PricingEngine();
        PricingResult result = engine.calculate(Arrays.asList(50.0), Arrays.asList(1), CustomerType.REGULAR, "SAVE10");
        assertEquals(48.0, result.getFinalPrice(), 0.01);
    }
}
