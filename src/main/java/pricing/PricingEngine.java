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
}
