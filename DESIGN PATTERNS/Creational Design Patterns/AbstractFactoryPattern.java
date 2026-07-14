interface Sedan {
    void showSedan();
}

interface SUV {
    void showSUV();
}

class EconomicSedan implements Sedan {
    @Override
    public void showSedan() {
        System.out.println("This is an Economic Sedan");
    }
}

class LuxurySedan implements Sedan {
    @Override
    public void showSedan() {
        System.out.println("This is a Luxury Sedan");
    }
}

class EconomicSUV implements SUV {
    @Override
    public void showSUV() {
        System.out.println("This is an Economic SUV");
    }
}

class LuxurySUV implements SUV {
    @Override
    public void showSUV() {
        System.out.println("This is a Luxury SUV");
    }
}

interface CarFactory {
    Sedan createSedan();

    SUV createSUV();
}

class EconomicCarFactory implements CarFactory {

    @Override
    public Sedan createSedan() {
        return new EconomicSedan();
    }

    @Override
    public SUV createSUV() {
        return new EconomicSUV();
    }
}

class LuxuryCarFactory implements CarFactory {

    @Override
    public Sedan createSedan() {
        return new LuxurySedan();
    }

    @Override
    public SUV createSUV() {
        return new LuxurySUV();
    }

}

public class AbstractFactoryPattern {
    public static void main(String[] args) {
        CarFactory cf1 = new EconomicCarFactory();
        Sedan economicSedan = cf1.createSedan();
        economicSedan.showSedan();
    }
}