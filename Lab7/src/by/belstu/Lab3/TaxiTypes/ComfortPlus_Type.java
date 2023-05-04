package by.belstu.Lab3.TaxiTypes;

import by.belstu.Lab3.Cars.CarType;
import by.belstu.Lab3.Cars.Taxi;


public class ComfortPlus_Type extends Taxi {
    private final CarType type = CarType.comfortPlus;
    private double taxiFare = 1.5;

    public ComfortPlus_Type() {}

    public ComfortPlus_Type(String name, double fuel, int speed) {
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
        System.out.println("Вы проехали " + km + " км в " + getName() + " (Комфорт+). С Вас " + priceForRide + " р. Спасибо за поездку!");
        return getProfit();
    }
}
