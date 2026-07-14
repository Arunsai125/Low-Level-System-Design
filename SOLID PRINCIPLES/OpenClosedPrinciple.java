/*
    SOLID - Open/Closed Principle (OCP)

    Definition:
    Software entities (classes, modules, functions, etc.)
    should be open for extension but closed for modification.

    We should be able to add new functionality without
    changing existing code.
*/

/*
====================================================
BAD EXAMPLE - Violates Open/Closed Principle
====================================================

Problem:
Every time we add a new payment type, we need to modify
the existing PaymentProcessor class.

Adding new functionality requires changing existing code.

class PaymentProcessor {
    public void processPayment(String paymentType) {
        if(paymentType.equals("CreditCard")) {
            System.out.println("Payment made with credit card.");
        } else if(paymentType.equals("PayPal")) {
            System.out.println("Payment made with PayPal.");
        } else if(paymentType.equals("UPI")) {
            System.out.println("Payment made with UPI.");
        } else {
            System.out.println("Unsupported payment type.");
        }
    }
}
*/

//====================================================
// GOOD EXAMPLE - Open/Closed Principle
//====================================================

interface Payment {
    void pay();
}

class CreditCardPayment implements Payment {
    public void pay() {
        System.out.println("Payment made with credit card.");
    }
}

class PayPalPayment implements Payment {
    public void pay() {
        System.out.println("Payment made with PayPal.");
    }
}

class UPIPayment implements Payment {
    public void pay() {
        System.out.println("Payment made with UPI.");
    }
}

class PaymentProcessor {
    public void processPayment(Payment payment) {
        payment.pay();
    }
}

public class OpenClosedPrinciple {
    public static void main(String[] args) {
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        paymentProcessor.processPayment(new CreditCardPayment());
        paymentProcessor.processPayment(new PayPalPayment());
        paymentProcessor.processPayment(new UPIPayment());
    }
}