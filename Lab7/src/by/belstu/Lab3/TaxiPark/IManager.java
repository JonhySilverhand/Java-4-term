package by.belstu.Lab3.TaxiPark;


import by.belstu.Lab3.Cars.Car;

import java.util.ArrayList;

public interface IManager
{
    public double getGeneralProfit();
    public double getCompanyProfit();
    public ArrayList<Car> searchSpeed(int min, int max);
    public void sortFuel();
}
