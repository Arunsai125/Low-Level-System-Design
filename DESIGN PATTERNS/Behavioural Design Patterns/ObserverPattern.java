import java.util.*;

interface Observer {
    void update(float temperature);
}

interface Observable {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}

class WeatherStation implements Observable {
    private List<Observer> observers = new ArrayList<>();
    private float temperature;

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update(temperature);
        }
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers();
    }
}

class MobileApp implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("The MobileApp has recieved the updated tempearture of :" + temperature + " ^C !");
    }
}

class TVDisplay implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("The TVDisplay has recieved the updated tempearture of :" + temperature + " ^C !");
    }
}

public class ObserverPattern {
    public static void main(String[] args) {
        Observer mobileApp = new MobileApp();
        Observer tvDisplay = new TVDisplay();
        WeatherStation weatherStation = new WeatherStation();
        weatherStation.addObserver(mobileApp);
        weatherStation.addObserver(tvDisplay);
        weatherStation.setTemperature(32.4f);
    }
}