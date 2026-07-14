/*
    SOLID - Liskov Substitution Principle (LSP)

    Definition:
    Subtypes must be substitutable for their base types
    without altering the correctness of the program.

    A child class should extend the behavior of the parent
    class without breaking the expectations of the parent.
*/

/*
====================================================
BAD EXAMPLE - Violates Liskov Substitution Principle
====================================================

Problem:
The Bird class assumes every bird can fly.

However, Ostrich cannot fly.
By forcing Ostrich to implement fly(), we break the
behavior expected from the parent class.


class Bird {
    public void eat() {
        System.out.println("Bird is eating.");
    }

    public void fly() {
        System.out.println("Bird is flying.");
    }
}

class Sparrow extends Bird {
    public void eat() {
        System.out.println("Sparrow is eating.");
    }

    public void fly() {
        System.out.println("Sparrow is flying.");
    }
}

class Ostrich extends Bird {
    public void eat() {
        System.out.println("Ostrich is eating.");
    }

    public void fly() {
        throw new UnsupportedOperationException(
            "Ostrich cannot fly."
        );
    }
}
*/

//====================================================
// GOOD EXAMPLE - Liskov Substitution Principle
//====================================================

interface Bird {
    void eat();
}

interface FlyingBird extends Bird {
    void fly();
}

class Sparrow implements FlyingBird {
    public void eat() {
        System.out.println("Sparrow is eating.");
    }

    public void fly() {
        System.out.println("Sparrow is flying.");
    }
}

class Ostrich implements Bird {
    public void eat() {
        System.out.println("Ostrich is eating.");
    }
}

public class LiskovSubstitutionPrinciple {
    public static void main(String[] args) {
        FlyingBird sparrow = new Sparrow();
        sparrow.eat();
        sparrow.fly();

        Bird ostrich = new Ostrich();
        ostrich.eat();
    }
}