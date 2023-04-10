package TaxiTypes;

import Cars.CarType;
import Cars.Taxi;
import TaxiExceptions.TaxiExceptions;

public class Truck_Type extends Taxi {
    private final CarType truck_type = CarType.truck;
    private double taxiFare = 1.7;

    public Truck_Type(){}
    public Truck_Type(String name, double fuel, int speed) throws TaxiExceptions {
        super(name, fuel, speed);
    }
    @Override
    public CarType getType_taxi() {
        return truck_type;
    }
    @Override
    public double getTaxi_Fare() {
        return taxiFare;
    }
    @Override
    public double ride(double km) throws TaxiExceptions{
        if(km < 0 || km > 200)
            throw new TaxiExceptions("Некорректный ввод пути");

        double income = taxiFare * km;
        String priceForRide = String.format("%.2f", income);
        double loss = getFuelRate() * km;
        this.setProfit(getProfit() + income - loss);
        String res_km = String.format("%.2f", km);
        System.out.println("Вы проехали " + res_km + " км в " + getTaxi_name() + " (Грузовое). С вас " + priceForRide + " BYN. Благодарим за поездку в нашем такси!");
        return getProfit();
    }

    @Override
    public String toString() {
        return "Truck_Type{" +
                "\ntruck_type=" + truck_type +
                "\nTaxi name: " + getTaxi_name() +
                "\nSpeed: " + getTaxi_speed() +
                "\ntaxiFare=" + taxiFare +
                '}';
    }
}
