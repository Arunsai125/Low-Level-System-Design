/*
    SOLID - Interface Segregation Principle (ISP)

    Definition:
    A class should not be forced to implement interfaces
    it does not use.

    Instead of creating large interfaces, create smaller
    and more specific interfaces.
*/

/*
====================================================
BAD EXAMPLE - Violates Interface Segregation Principle
====================================================

Problem:
The Worker interface contains multiple responsibilities.

A class implementing Worker must provide implementations
for methods it may not need.

For example, a Tester should not be forced to implement
code() and design().


interface Worker {
    void code();
    void test();
    void design();
}

class Developer implements Worker {

    public void code() {
        System.out.println("Developer is coding.");
    }

    public void test() {
        System.out.println("Developer is testing.");
    }

    public void design() {
        System.out.println("Developer is designing.");
    }
}

class Tester implements Worker {

    public void code() {
        throw new UnsupportedOperationException(
            "Tester cannot code."
        );
    }

    public void test() {
        System.out.println("Tester is testing.");
    }

    public void design() {
        throw new UnsupportedOperationException(
            "Tester cannot design."
        );
    }
}
*/

//====================================================
// GOOD EXAMPLE - Interface Segregation Principle
//====================================================

interface Coder {
    void code();
}

interface Tester {
    void test();
}

interface Designer {
    void design();
}

class Developer implements Coder, Tester {

    public void code() {
        System.out.println("Developer is coding.");
    }

    public void test() {
        System.out.println("Developer is testing.");
    }
}

class UIUXEngineer implements Designer {

    public void design() {
        System.out.println("UI/UX Engineer is designing.");
    }
}

public class InterfaceSegregationPrinciple {
    public static void main(String[] args) {
        Developer developer = new Developer();
        developer.code();
        developer.test();

        UIUXEngineer designer = new UIUXEngineer();
        designer.design();
    }
}