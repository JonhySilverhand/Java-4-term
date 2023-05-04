package by.belstu.Lab3.TaxiTypes;

import by.belstu.Lab3.Cars.CarType;
import by.belstu.Lab3.Cars.Taxi;


public class Truck_Type extends Taxi {
    private final CarType type = CarType.truck;
    private double taxiFare = 1.7;

    public Truck_Type(){}
    public Truck_Type(String name, double fuel, int speed)  {
        super(name, fuel, speed);
    }

    @Override
    public CarType getType() {
        return type;
    }
    @Override
    public double getTaxiFare() {
        return taxiFare;
    }
    @Override
    public double ride(double km){


        double income = taxiFare * km;
        String priceForRide = String.format("%.2f", income);
        double loss = getFuelRate() * km;
        this.setProfit(getProfit() + income - loss);
        System.out.println("Вы проехали " + km + " км в " + getName() + " (Грузовое). С вас " + priceForRide + " руб. Благодарим за поездку в нашем такси!");
        return getProfit();
    }
}
