package pricing;

import pricing.model.CustomerType;
import pricing.model.PricingResult;
import pricing.service.DiscountService;
import pricing.service.TaxService;

import java.util.List;

public class PricingEngine {
    private final DiscountService discountService;
    private final TaxService taxService;

    public PricingEngine() {
        this.discountService = new DiscountService();
        this.taxService = new TaxService();
    }

    public PricingResult calculate(List<Double> prices, List<Integer> quantities, CustomerType customerType, String discountCode) {
        double subtotal = calculateSubtotal(prices, quantities);
        double discountAmount = discountService.calculateDiscount(subtotal, customerType, discountCode);
        double taxableAmount = subtotal - discountAmount;
        double tax = taxService.calculateTax(taxableAmount);
        double finalPrice = taxableAmount + tax;

        return new PricingResult(subtotal, discountAmount, tax, finalPrice);
    }

    private double calculateSubtotal(List<Double> prices, List<Integer> quantities) {
        double subtotal = 0;
        for (int i = 0; i < prices.size(); i++) {
            subtotal += prices.get(i) * quantities.get(i);
        }
        return subtotal;
    }

    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Usage: PricingEngine <prices> <quantities> <customerType> <discountCode>");
            return;
        }

        String[] pricesStr = args[0].split(",");
        String[] quantitiesStr = args[1].split(",");
        String customerTypeStr = args[2];
        String discountCode = args[3].equals("null") ? null : args[3];

        java.util.ArrayList<Double> prices = new java.util.ArrayList<>();
        for (String p : pricesStr) prices.add(Double.parseDouble(p));

        java.util.ArrayList<Integer> quantities = new java.util.ArrayList<>();
        for (String q : quantitiesStr) quantities.add(Integer.parseInt(q));

        CustomerType customerType = CustomerType.valueOf(customerTypeStr);

        PricingEngine engine = new PricingEngine();
        PricingResult result = engine.calculate(prices, quantities, customerType, discountCode);

        System.out.println(result);
    }
}
