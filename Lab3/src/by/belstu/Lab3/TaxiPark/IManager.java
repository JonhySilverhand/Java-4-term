package by.belstu.Lab3.TaxiPark;

import by.belstu.Lab3.TaxiExceptions.TaxiExceptions;

public interface IManager
{
    public double getGeneralProfit();
    public double getCompanyProfit();
    public void searchSpeed(int min, int max) throws TaxiExceptions;
    public void sortFuel();
}
