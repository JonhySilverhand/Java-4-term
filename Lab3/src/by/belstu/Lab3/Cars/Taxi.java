package by.belstu.Lab3.Cars;
import by.belstu.Lab3.TaxiExceptions.TaxiExceptions;

public class Taxi extends Car implements Comparable<Taxi> {
    private String name;
    private CarType type;
    private double fuelRate;
    private int speed;
    private double taxiFare = 1;
    private double profit = 0;

    public Taxi() {}

    public Taxi(String name, double fuel, int speed) throws TaxiExceptions
    {
        if(name == null || name.length() < 3 || name.length() > 30 || fuel < 0 || fuel > 5 || speed > 210 || speed < 0)
            throw new TaxiExceptions("Ошибка в конструкторе Taxi");
        this.name = name;
        this.fuelRate = fuel;
        this.speed = speed;
    }

    @Override
    public int compareTo(Taxi o) {
        return Integer.compare(this.getSpeed(), o.getSpeed());
    }
    public double ride(double km) throws TaxiExceptions       /// метод для поездок, который будем переопределять в каждом классе
    {
        if (km < 0)
            throw new TaxiExceptions("Kilometres are less than zero");
        double income = taxiFare * km;
        String priceForRide = String.format("%.2f", income);
        double loss = getFuelRate() * km;
        this.setProfit(getProfit() + income - loss);
        System.out.println("Вы проехали " + km + " км в " + getName() + ". С Вас " + priceForRide + " р. Спасибо за поездку!");
        return getProfit();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public double getFuelRate() {
        return fuelRate;
    }

    public void setFuelRate(double fuelRate) {
        this.fuelRate = fuelRate;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getTaxiFare() {
        return taxiFare;
    }

    public void setTaxiFare(double taxiFare) {
        this.taxiFare = taxiFare;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}
