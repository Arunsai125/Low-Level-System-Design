/*
    SOLID - Single Responsibility Principle (SRP)

    Definition:
    A class should have only one reason to change.
    Meaning, a class should have only one responsibility.

    A class should focus on doing one thing well.
*/

/*
====================================================
BAD EXAMPLE - Violates Single Responsibility Principle
====================================================

Problem:
The Invoice class has multiple responsibilities:

1. Calculate invoice total
2. Print invoice
3. Save invoice to database

If the printing logic changes, Invoice changes.
If database logic changes, Invoice changes.

The class has multiple reasons to change.


class Invoice {
    private double amount;
    private double tax;
    public Invoice(double amount, double tax) {
        this.amount = amount;
        this.tax = tax;
    }
    public double calculateTotal() {
        return amount + tax;
    }
    public void printInvoice() {
        System.out.println(
            "Invoice Amount: " + calculateTotal()
        );
    }
    public void saveInvoice() {
        System.out.println(
            "Invoice saved to database"
        );
    }
}
*/

//====================================================
// GOOD EXAMPLE - Single Responsibility Principle
//====================================================

class Invoice {
    private double amount;
    private double tax;

    public Invoice(double amount, double tax) {
        this.amount = amount;
        this.tax = tax;
    }

    public double calculateTotal() {
        return amount + tax;
    }
}

class InvoicePrinter {
    public void printInvoice(Invoice invoice) {
        System.out.println(
                "Invoice Amount: " + invoice.calculateTotal());
    }
}

class InvoiceRepository {
    public void saveInvoice(Invoice invoice) {
        System.out.println(
                "Invoice saved to database: "
                        + invoice.calculateTotal());
    }
}

public class SingleResponsibilityPrinciple {
    public static void main(String[] args) {
        Invoice invoice = new Invoice(100, 10);
        InvoicePrinter printer = new InvoicePrinter();
        printer.printInvoice(invoice);
        InvoiceRepository repository = new InvoiceRepository();
        repository.saveInvoice(invoice);
    }
}