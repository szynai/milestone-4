package oakwood;

public class ReservationPayment extends PaymentFramework {
    private double paymentReceived;
    private double change;

    public ReservationPayment(double amount, double discountRate, String paymentMethod, double paymentReceived) {
        this.amount = amount;
        this.discountRate = discountRate;
        this.paymentMethod = paymentMethod;
        this.paymentReceived = paymentReceived;
    }

    @Override
    protected boolean validatePayment() {
        if (paymentMethod == null || paymentMethod.trim().isEmpty()) {
            System.out.println("No payment method provided.");
            return false;
        }

        if (paymentReceived < totalAmount) {
            System.out.printf("Insufficient payment. Need PHP %.2f, received PHP %.2f%n",
                    totalAmount, paymentReceived);
            return false;
        }

        return true;
    }

    @Override
    protected void finalizeTransaction() {
        change = paymentReceived - totalAmount;

        System.out.printf("Payment Method: %s%n", paymentMethod);
        System.out.printf("Payment Received: PHP %.2f%n", paymentReceived);
        System.out.printf("Change: PHP %.2f%n", change);
    }

    public double getPaymentReceived() {
        return paymentReceived;
    }

    public double getChange() {
        return change;
    }
}

