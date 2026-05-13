
package oakwood;

public abstract class PaymentFramework {
    protected double amount;
    protected double discountRate;
    protected String paymentMethod;

    protected final double VATRate = 0.12;

    protected double vatAmount;
    protected double discountAmount;
    protected double totalAmount;

    protected abstract boolean validatePayment();

    protected abstract void finalizeTransaction();

    protected double applyVatRate(double amount) {
        vatAmount = amount * VATRate;
        return amount + vatAmount;
    }

    protected double applyDiscount(double amount) {
        discountAmount = amount * discountRate;
        return amount - discountAmount;
    }

    public boolean processInvoice() {
        System.out.println("\n=== Processing Invoice ===");
        System.out.printf("Original Amount: PHP %.2f%n", amount);

        double vatInclusiveAmount = applyVatRate(amount);
        totalAmount = applyDiscount(vatInclusiveAmount);

        System.out.printf("VAT (12%%): +PHP %.2f%n", vatAmount);
        System.out.printf("Discount (%.0f%%): -PHP %.2f%n", discountRate * 100, discountAmount);
        System.out.printf("Total Amount Due: PHP %.2f%n", totalAmount);

        if (!validatePayment()) {
            System.out.println("Payment validation failed. Transaction aborted.");
            return false;
        }

        finalizeTransaction();

        System.out.println("=== Invoice Complete ===\n");
        return true;
    }

    public double getAmount() {
        return amount;
    }

    public double getVatAmount() {
        return vatAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}

