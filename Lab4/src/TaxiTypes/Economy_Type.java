package TaxiTypes;

import Cars.CarType;
import Cars.Taxi;
import TaxiExceptions.TaxiExceptions;

public class Economy_Type extends Taxi
{
    private final CarType type = CarType.economy;
    private double taxiFare = 1.2;

    public Economy_Type() {}

    public Economy_Type(String name, double fuel, int speed) throws TaxiExceptions {
        super(name, fuel, speed);
    }


    @Override
    public CarType getType_taxi() {
        return type;
    }
    @Override
    public double getTaxi_Fare() {
        return taxiFare;
    }
    @Override
    public double ride(double km) throws TaxiExceptions {
        if (km < 0 || km > 200)
            throw new TaxiExceptions("Invalid input of kilometres");
        double income = taxiFare * km;
        String priceForRide = String.format("%.2f", income);
        double loss = getFuelRate() * km;
        this.setProfit(getProfit() + income - loss);
        String res_km = String.format("%.2f", km);
        System.out.println("Вы проехали " + res_km + " км в " + getTaxi_name() + " (Эконом). С Вас " + priceForRide + " BYN. Благодарим за поездку в нашем такси!");
        return getProfit();
    }

    @Override
    public String toString() {
        return "Economy_Type{" +
                "\ntype=" + type +
                "\nTaxi name: " + getTaxi_name() +
                "\nSpeed: " + getTaxi_speed() +
                "\ntaxiFare=" + taxiFare +
                '}';
    }
}
