package pricing;

import java.util.List;

/**
 * BAD DESIGN: One class, one method, multiple responsibilities, 
 * hardcoded values, and poor readability.
 */
public class PricingEngine {
    
    public double calculatePrice(List<Double> prices, List<Integer> quantities, String customerType, String discountCode) {
        double subtotal = 0;
        for (int i = 0; i < prices.size(); i++) {
            subtotal += prices.get(i) * quantities.get(i);
        }

        double discount = 0;
        if (customerType.equals("VIP")) {
            discount = subtotal * 0.15;
        } else if (customerType.equals("REGULAR")) {
            if (subtotal > 100) {
                discount = subtotal * 0.05;
            }
        }

        if (discountCode != null && discountCode.equals("SAVE10")) {
            discount += 10;
        } else if (discountCode != null && discountCode.equals("SAVE20")) {
            discount += 20;
        }

        double taxableAmount = subtotal - discount;
        double tax = taxableAmount * 0.20;
        double finalPrice = taxableAmount + tax;

        System.out.println("Subtotal: " + subtotal);
        System.out.println("Discount: " + discount);
        System.out.println("Tax: " + tax);
        System.out.println("Final Price: " + finalPrice);

        return finalPrice;
    }
}
