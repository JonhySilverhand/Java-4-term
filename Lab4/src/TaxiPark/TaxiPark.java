package TaxiPark;
import Cars.Taxi;
import TaxiExceptions.TaxiExceptions;
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
            double generalProfit = 0;
            for(Taxi taxi : getTaxiList())
                generalProfit += taxi.getProfit();
            return generalProfit;
        }

        public double getCompanyProfit() {
            return getGeneralProfit() * 0.66;
        }

        public void searchSpeed(int min, int max) throws TaxiExceptions {
            if(max < min || min < 0)
                throw new TaxiExceptions("Некорректный ввод минимальной или максимальной скорости");
            System.out.println("\nАвтомобили со скоростью " + min + "-" + max + " км/ч");
            for (Taxi taxi : getTaxiList())
                if(taxi.getTaxi_speed() > min && taxi.getTaxi_speed() < max)
                    System.out.println(taxi.getTaxi_name() + " (" + taxi.getTaxi_speed() + " км/ч)");
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
                System.out.println(taxi.getTaxi_name() + " (" + taxi.getTaxi_speed() + " км/ч)");
        }
    }
}
