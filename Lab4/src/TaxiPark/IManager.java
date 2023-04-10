package TaxiPark;

import TaxiExceptions.TaxiExceptions;

public interface IManager
{
    public double getGeneralProfit();
    public double getCompanyProfit();
    public void searchSpeed(int min, int max) throws TaxiExceptions;
    public void sortFuel();
}
