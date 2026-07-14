import java.util.*;

// Strategy Pattern - Pricing (Weekdays + Weekends)

enum VehicleType {
    CAR,
    BIKE,
    TRUCK,
    AUTO
}

enum DurationType {
    HOURS,
    DAYS
}

interface PricingStrategy {
    double calculateTotalParkingFare(VehicleType vehicleType, DurationType durationType, int duration);
}

class EconomicPricingStrategy implements PricingStrategy {
    @Override
    public double calculateTotalParkingFare(VehicleType vehicleType, DurationType durationType, int duration) {
        double hourlyRate = getHourlyRate(vehicleType);
        if (durationType == DurationType.HOURS) {
            return duration * hourlyRate;
        }
        return duration * hourlyRate * 24;
    }

    public double getHourlyRate(VehicleType vehicleType) {
        switch (vehicleType) {
            case CAR:
                return 10.0;
            case BIKE:
                return 5.0;
            case TRUCK:
                return 15.0;
            case AUTO:
                return 8.0;
            default:
                return 15.0;
        }
    }
}

class PremiumPricingStrategy implements PricingStrategy {
    @Override
    public double calculateTotalParkingFare(VehicleType vehicleType, DurationType durationType, int duration) {
        double hourlyRate = getHourlyRate(vehicleType);
        if (durationType == DurationType.HOURS) {
            return hourlyRate * duration;
        }
        return hourlyRate * duration * 24;
    }

    public double getHourlyRate(VehicleType vehicleType) {
        switch (vehicleType) {
            case CAR:
                return 15.0;
            case BIKE:
                return 10.0;
            case TRUCK:
                return 20.0;
            case AUTO:
                return 13.0;
            default:
                return 20.0;
        }
    }
}

class PricingService {
    private PricingStrategy pricingStrategy;

    public PricingService(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public double calculateAmount(VehicleType vehicleType, DurationType durationType, int duration) {
        return pricingStrategy.calculateTotalParkingFare(vehicleType, durationType, duration);
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }
}

// Factory Pattern - Vehicle

abstract class Vehicle {
    private String licensePlateNumber;
    private VehicleType vehicleType;

    public Vehicle(String licensePlateNumber, VehicleType vehicleType) {
        this.licensePlateNumber = licensePlateNumber;
        this.vehicleType = vehicleType;
    }

    public String getLicensePlateNumber() {
        return this.licensePlateNumber;
    }

    public VehicleType getVehicleType() {
        return this.vehicleType;
    }

    @Override
    public String toString() {
        return vehicleType + " - " + licensePlateNumber;
    }

}

class Bike extends Vehicle {
    public Bike(String licensePlateNumber) {
        super(licensePlateNumber, VehicleType.BIKE);
    }
}

class Car extends Vehicle {
    public Car(String licensePlateNumber) {
        super(licensePlateNumber, VehicleType.CAR);
    }
}

class Truck extends Vehicle {
    public Truck(String licensePlateNumber) {
        super(licensePlateNumber, VehicleType.TRUCK);
    }
}

class Auto extends Vehicle {
    public Auto(String licensePlateNumber) {
        super(licensePlateNumber, VehicleType.AUTO);
    }
}

class VehicleFactory {
    public static Vehicle getVehicle(VehicleType vehicleType, String licensePlateNumber) {
        switch (vehicleType) {
            case BIKE:
                return new Bike(licensePlateNumber);
            case CAR:
                return new Car(licensePlateNumber);
            case AUTO:
                return new Auto(licensePlateNumber);
            case TRUCK:
                return new Truck(licensePlateNumber);
            default:
                throw new IllegalArgumentException("Vehicle type is not supported for parking");
        }
    }
}

// Strategy Pattern - Payment Method

interface PaymentStrategy {
    boolean processPayment(double amount);
}

class CardPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Card Payment has been processed");
        return true;
    }
}

class CashPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Cash Payment has been processed");
        return true;
    }
}

class PaymentService {
    private PaymentStrategy paymentStrategy;

    public PaymentService(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public boolean makePayment(double amount) {
        if (amount <= 0.0) {
            System.out.println("Invalid Payment Amount !");
            return false;
        }
        return paymentStrategy.processPayment(amount);
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
}

class ParkingSpot {
    private int spotNumber;
    private boolean isOccupied;
    private VehicleType spotType;
    private Vehicle parkedVehicle;

    public ParkingSpot(int spotNumber, VehicleType spotType) {
        this.spotNumber = spotNumber;
        this.spotType = spotType;
        this.isOccupied = false;
    }

    public boolean canParkVehicle(Vehicle vehicle) {
        return !isOccupied && vehicle.getVehicleType() == spotType;
    }

    public void parkVehicle(Vehicle vehicle) {
        if (!canParkVehicle(vehicle))
            throw new IllegalArgumentException("Vehicle cannot be parked here !");
        this.parkedVehicle = vehicle;
        this.isOccupied = true;
    }

    public void vacate() {
        if (!isOccupied)
            throw new IllegalStateException("The spot is already vacant");
        this.parkedVehicle = null;
        this.isOccupied = false;
    }

    public int getSpotNumber() {
        return this.spotNumber;
    }

    public boolean isSpotOccupied() {
        return this.isOccupied;
    }

    public VehicleType getSpotType() {
        return this.spotType;
    }

    public Vehicle getParkedVehicle() {
        return this.parkedVehicle;
    }

    @Override
    public String toString() {
        return "SpotNumber: " + spotNumber + " spotType:" + spotType + "isOccupied: " + isOccupied + "VehicleType: "
                + spotType;
    }

}

class ParkingFloor {
    private int floorNumber;
    private List<ParkingSpot> parkingSpots;

    public ParkingFloor(int floorNumber, List<ParkingSpot> parkingSpots) {
        this.floorNumber = floorNumber;
        this.parkingSpots = parkingSpots;
    }

    public ParkingSpot findAvailableParkingSpot(VehicleType vehicleType) {
        for (ParkingSpot spot : parkingSpots) {
            if (!spot.isSpotOccupied() && spot.getSpotType() == vehicleType)
                return spot;
        }
        return null;
    }

    public int getFloorNumber() {
        return this.floorNumber;
    }

    public List<ParkingSpot> getParkingSpots() {
        return this.parkingSpots;
    }
}

class ParkingLot {
    private List<ParkingFloor> parkingFloors;

    public ParkingLot(List<ParkingFloor> parkingFloors) {
        this.parkingFloors = parkingFloors;
    }

    public ParkingSpot getAvailableParkingSpot(VehicleType vehicleType) {
        for (ParkingFloor parkingFloor : parkingFloors) {
            ParkingSpot spot = parkingFloor.findAvailableParkingSpot(vehicleType);
            if (spot != null)
                return spot;
        }
        return null;
    }

    public ParkingSpot parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = getAvailableParkingSpot(vehicle.getVehicleType());
        if (spot == null) {
            System.out.println("No available parking spots for the vehicle type : " + vehicle.getVehicleType());
            return null;
        }
        spot.parkVehicle(vehicle);
        return spot;
    }

    public void vacateSpot(ParkingSpot spot) {
        if (spot == null)
            System.out.println("Invalid Spot");
        else if (!spot.isSpotOccupied())
            System.out.println("Spot is already vacant");
        else {
            spot.vacate();
        }
    }

    public List<ParkingFloor> getParkingFloors() {
        return this.parkingFloors;
    }

}

public class ParkingLotLLD {
    public static void main(String[] args) {

        // 1. Create parking spots for floor 1
        List<ParkingSpot> floorOneSpots = Arrays.asList(
                new ParkingSpot(1, VehicleType.CAR),
                new ParkingSpot(2, VehicleType.CAR),
                new ParkingSpot(3, VehicleType.BIKE),
                new ParkingSpot(4, VehicleType.AUTO),
                new ParkingSpot(5, VehicleType.TRUCK));

        ParkingFloor floorOne = new ParkingFloor(1, floorOneSpots);

        // 2. Create parking spots for floor 2
        List<ParkingSpot> floorTwoSpots = Arrays.asList(
                new ParkingSpot(6, VehicleType.CAR),
                new ParkingSpot(7, VehicleType.BIKE),
                new ParkingSpot(8, VehicleType.BIKE),
                new ParkingSpot(9, VehicleType.AUTO),
                new ParkingSpot(10, VehicleType.TRUCK));

        ParkingFloor floorTwo = new ParkingFloor(2, floorTwoSpots);

        // 3. Create parking lot with multiple floors
        ParkingLot parkingLot = new ParkingLot(Arrays.asList(floorOne, floorTwo));

        // 4. Create vehicles using Factory Pattern
        Vehicle car = VehicleFactory.getVehicle(VehicleType.CAR, "CAR-123");
        Vehicle bike = VehicleFactory.getVehicle(VehicleType.BIKE, "BIKE-456");
        Vehicle truck = VehicleFactory.getVehicle(VehicleType.TRUCK, "TRUCK-789");

        // 5. Park vehicles
        ParkingSpot carSpot = parkingLot.parkVehicle(car);
        ParkingSpot bikeSpot = parkingLot.parkVehicle(bike);
        ParkingSpot truckSpot = parkingLot.parkVehicle(truck);

        System.out.println(car + " parked at: " + carSpot);
        System.out.println(bike + " parked at: " + bikeSpot);
        System.out.println(truck + " parked at: " + truckSpot);

        // 6. Calculate parking fee using Strategy Pattern
        PricingService pricingService = new PricingService(new EconomicPricingStrategy());

        double carFee = pricingService.calculateAmount(
                car.getVehicleType(),
                DurationType.HOURS,
                3);

        System.out.println("Car parking fee for 3 hours: $" + carFee);

        pricingService.setPricingStrategy(new PremiumPricingStrategy());

        double weekendCarFee = pricingService.calculateAmount(
                car.getVehicleType(),
                DurationType.HOURS,
                3);

        System.out.println("Weekend/Premium car parking fee for 3 hours: $" + weekendCarFee);

        // 7. Process payment using Payment Strategy Pattern
        PaymentService paymentService = new PaymentService(new CardPayment());

        boolean paymentSuccess = paymentService.makePayment(carFee);

        if (paymentSuccess) {
            System.out.println("Payment successful. Vacating car spot...");
            parkingLot.vacateSpot(carSpot);
        }

        // 8. Show spot after vacating
        System.out.println("Car spot after vacating: " + carSpot);
    }
}