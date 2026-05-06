package pricing.service;

import pricing.model.CustomerType;

public class DiscountService {
    
    public double calculateDiscount(double subtotal, CustomerType customerType, String discountCode) {
        double discount = 0;
        
        if (customerType == CustomerType.VIP) {
            discount = subtotal * 0.15;
        } else if (customerType == CustomerType.REGULAR) {
            if (subtotal > 100) {
                discount = subtotal * 0.05;
            }
        }

        if (discountCode != null) {
            switch (discountCode) {
                case "SAVE10":
                    discount += 10;
                    break;
                case "SAVE20":
                    discount += 20;
                    break;
            }
        }
        
        return discount;
    }
}
