package by.belstu.Lab3.TaxiTypes;

import by.belstu.Lab3.Cars.CarType;
import by.belstu.Lab3.Cars.Taxi;


public class Economy_Type extends Taxi
{
    private final CarType type = CarType.economy;
    private double taxiFare = 1.2;

    public Economy_Type() {}

    public Economy_Type(String name, double fuel, int speed){
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
        System.out.println("Вы проехали " + km + " км в " + getName() + " (Эконом). С Вас " + priceForRide + " р. Спасибо за поездку!");
        return getProfit();
    }
}
