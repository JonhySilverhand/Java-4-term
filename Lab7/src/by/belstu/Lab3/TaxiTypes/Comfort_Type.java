package by.belstu.Lab3.TaxiTypes;

import by.belstu.Lab3.Cars.Taxi;
import by.belstu.Lab3.Cars.CarType;

public class Comfort_Type extends Taxi {
    private final CarType type = CarType.comfort;
    private double taxiFare = 1.4;

    public Comfort_Type() { }
    public Comfort_Type(String name, double fuel, int speed){
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
        System.out.println("Вы проехали " + km + " км в " + getName() + " (Комфорт). С Вас " + priceForRide + " р. Спасибо за поездку!");
        return getProfit();
    }
}
