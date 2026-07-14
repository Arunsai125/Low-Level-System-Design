interface Pizza {
    String getDescription();

    int getCost();
}

class PlainPizza implements Pizza {

    @Override
    public String getDescription() {
        return "Plain Pizza";
    }

    @Override
    public int getCost() {
        return 10;
    }
}

abstract class PizzaDecorator implements Pizza {

    protected Pizza pizza;

    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }
}

class CheeseDecorator extends PizzaDecorator {

    public CheeseDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + " + Cheese";
    }

    @Override
    public int getCost() {
        return pizza.getCost() + 3;
    }
}

class PepperoniDecorator extends PizzaDecorator {

    public PepperoniDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + " + Pepperoni";
    }

    @Override
    public int getCost() {
        return pizza.getCost() + 4;
    }
}

class OliveDecorator extends PizzaDecorator {

    public OliveDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + " + Olives";
    }

    @Override
    public int getCost() {
        return pizza.getCost() + 2;
    }
}

public class DecoratorPattern {

    public static void main(String[] args) {

        Pizza pizza = new OliveDecorator(
                new PepperoniDecorator(
                        new CheeseDecorator(
                                new PlainPizza())));

        System.out.println("Pizza Ordered: " + pizza.getDescription());
        System.out.println("Pizza Cost: $" + pizza.getCost());
    }
}