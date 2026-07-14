interface Vehicle {
    void drive();
}

class Car implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving Car");
    }
}

class Truck implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving Truck");
    }
}

class VehicleFactory {
    public Vehicle getVehicle(String type) {
        if (type == null)
            throw new IllegalArgumentException("Vehicle type cannot be null");
        if (type.equalsIgnoreCase("car"))
            return new Car();
        else if (type.equalsIgnoreCase("truck"))
            return new Truck();
        else
            throw new IllegalArgumentException("Unknown vehicle type");
    }
}

public class FactoryPattern {
    public static void main(String[] args) {
        VehicleFactory vf = new VehicleFactory();
        vf.getVehicle("car").drive();
    }
}