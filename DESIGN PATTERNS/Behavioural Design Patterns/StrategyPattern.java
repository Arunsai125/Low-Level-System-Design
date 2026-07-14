interface PaymentStrategy {
    void makePayment(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    @Override
    public void makePayment(int amount) {
        System.out.println("A credit card payment of amount: " + amount + " has been processed!");
    }
}

class CashPayment implements PaymentStrategy {
    @Override
    public void makePayment(int amount) {
        System.out.println("A cash payment of amount: " + amount + " has been processed!");
    }
}

class PayPalPayment implements PaymentStrategy {
    @Override
    public void makePayment(int amount) {
        System.out.println("A paypal transaction of amount: " + amount + " has been processed!");
    }
}

class PaymentProcessor {
    private PaymentStrategy paymentStrategy;

    public PaymentProcessor(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment(int amount) {
        paymentStrategy.makePayment(amount);
    }
}

public class StrategyPattern {
    public static void main(String[] args) {
        PaymentStrategy paypal = new PayPalPayment();
        PaymentProcessor processor = new PaymentProcessor(paypal);
        processor.processPayment(25);
    }
}
