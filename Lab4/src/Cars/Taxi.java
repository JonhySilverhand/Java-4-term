package Cars;
import TaxiExceptions.TaxiExceptions;

public class Taxi extends Car implements Comparable<Taxi> {
    private String taxi_name;
    private CarType type_taxi;
    private double fuelRate;
    private int taxi_speed;
    private double taxi_Fare = 1;
    private double profit = 0;

    public Taxi() {}

    public Taxi(String name, double fuel, int speed) throws TaxiExceptions
    {
        if(name == null || name.length() < 3 || name.length() > 30 || fuel < 0 || fuel > 5 || speed > 210 || speed < 0)
            throw new TaxiExceptions("Ошибка в конструкторе Taxi");
        this.taxi_name = name;
        this.fuelRate = fuel;
        this.taxi_speed = speed;
    }

    @Override
    public int compareTo(Taxi o) {
        return Integer.compare(this.getTaxi_speed(), o.getTaxi_speed());
    }
    public double ride(double km) throws TaxiExceptions       /// метод для поездок, который будем переопределять в каждом классе
    {
        if (km < 0)
            throw new TaxiExceptions("Kilometres are less than zero");
        double income = taxi_Fare * km;
        String priceForRide = String.format("%.1f", income);
        double loss = getFuelRate() * km;
        this.setProfit(getProfit() + income - loss);
        System.out.println("Вы проехали " + km + " км в " + getTaxi_name() + ". С Вас " + priceForRide + " р. Спасибо за поездку!");
        return getProfit();
    }

    public String getTaxi_name() {
        return taxi_name;
    }

    public void setTaxi_name(String taxi_name) {
        this.taxi_name = taxi_name;
    }

    public CarType getType_taxi() {
        return type_taxi;
    }

    public void setType_taxi(CarType type_taxi) {
        this.type_taxi = type_taxi;
    }

    public double getFuelRate() {
        return fuelRate;
    }

    public void setFuelRate(double fuelRate) {
        this.fuelRate = fuelRate;
    }

    public int getTaxi_speed() {
        return taxi_speed;
    }

    public void setTaxi_speed(int taxi_speed) {
        this.taxi_speed = taxi_speed;
    }

    public double getTaxi_Fare() {
        return taxi_Fare;
    }

    public void setTaxi_Fare(double taxi_Fare) {
        this.taxi_Fare = taxi_Fare;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}
