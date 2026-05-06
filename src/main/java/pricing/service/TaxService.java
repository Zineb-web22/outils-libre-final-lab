package pricing.service;

public class TaxService {
    private static final double TAX_RATE = 0.20;

    public double calculateTax(double taxableAmount) {
        return taxableAmount * TAX_RATE;
    }
}
