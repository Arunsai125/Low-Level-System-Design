import java.util.*;

interface Observer{
    void update(String productName);
}
interface Observable{
    void addCustomer(Observer observer);
    void removeCustomer(Observer observer);
    void notifyCustomers();
}

class WalmartWarhouse implements Observable{
    private boolean isInStock;
    private String productName;
    private List<Observer> customerList;

    public WalmartWarhouse(String productName){
        this.isInStock = false;
        this.productName = productName;
        customerList = new ArrayList<>();
    }

    @Override
    public void addCustomer(Observer observer){
        customerList.add(observer);
    }

    @Override
    public void removeCustomer(Observer observer){
        customerList.remove(observer);
    }

    @Override
    public void notifyCustomers(){
        for(Observer obs : customerList){
            obs.update(productName);
        }
        customerList.clear();
    }

    public void setStock(boolean isInStock){
        this.isInStock = isInStock;
        if(isInStock){
            notifyCustomers();
        }
    }
}

class Customer implements Observer{
    private String customerName;
    private String customerEmail;

    public Customer(String customerName, String customerEmail){
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }

    @Override
    public void update(String productName){
        System.out.println("Hey " + customerName +  ", ! The product is back in stock");
        System.out.println("An E-mail has been rolled out to : " + customerEmail);
    }
}

public class NotifyMe{
    public static void main(String[] args){
        WalmartWarhouse warhouse = new WalmartWarhouse("Iphone 17 Pro Max");
        Observer arun = new Customer("Arun", "arun.narla@gmail.com");
        Observer revanth = new Customer("Revanth", "revanth.patha@gmail.com");
        warhouse.addCustomer(arun);
        warhouse.addCustomer(revanth);
        warhouse.setStock(true);
    }
}