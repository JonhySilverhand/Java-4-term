package by.belstu.Lab3.TaxiPark;
import by.belstu.Lab3.Cars.Car;
import by.belstu.Lab3.Cars.Taxi;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class TaxiPark
{
    private List<Taxi> taxiList = new ArrayList<>();
    public TaxiPark()
    {
        taxiList = new ArrayList<>();
    }
    public List<Taxi> getTaxiList(){
        return taxiList;
    }
    public void setTaxiList(List<Taxi> taxiList){
        this.taxiList = taxiList;
    }
    public void AddTaxi(Taxi taxi){
        taxiList.add(taxi);
    }
    public void DeleteTaxi(Taxi taxi){
        taxiList.remove(taxi);
    }
    public void DeleteAllTaxi(){
        taxiList.removeAll(taxiList);
    }

    public class Manager implements IManager {

        public double getGeneralProfit() {
            double generalProfit = 20.62;
            for(Taxi taxi : getTaxiList())
                generalProfit += taxi.getProfit();
            return generalProfit;
        }

        public double getCompanyProfit() {
            return getGeneralProfit() * 0.66;
        }

        public ArrayList<Car> searchSpeed(int min, int max){
            ArrayList<Car> carList = new ArrayList<Car>();
            System.out.println("\nАвтомобили со скоростью " + min + "-" + max + " км/ч");
            for (Taxi taxi : getTaxiList())
                if(taxi.getSpeed() > min && taxi.getSpeed() < max)
                    carList.add(taxi);

            return carList;
        }

        public void sortFuel() {
            getTaxiList().sort(new Comparator<Taxi>() {
                @Override
                public int compare(Taxi t1, Taxi t2) {
                    return t1.compareTo(t2);
                }
            });
            System.out.println("\nОтсортированный таксопарк по скорости:");
            for(Taxi taxi : getTaxiList())
                System.out.println(taxi.getName() + " (" + taxi.getSpeed() + " км/ч)");
        }
    }
}
