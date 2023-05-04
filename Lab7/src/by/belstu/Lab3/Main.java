package by.belstu.Lab3;

import by.belstu.Lab3.Cars.Taxi;
import by.belstu.Lab3.TaxiPark.TaxiPark;
import by.belstu.Lab3.TaxiTypes.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;


public class Main {
    static{
        new DOMConfigurator().doConfigure("log/log4j.xml",
                LogManager.getLoggerRepository());
    }
    private static final Logger LOG = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        try {
            LOG.info("Starting program_____________________________");

            Economy_Type polo = new Economy_Type("Volkswagen Polo", 0.11, 130);
            polo.ride(3);
            polo.ride(2.4);

            Comfort_Type lancer = new Comfort_Type("Mitsubishi Lancer", 0.13, 190);
            lancer.ride(5.3);

            ComfortPlus_Type MB = new ComfortPlus_Type("Mercedes-Benz E200", 0.15, 200);
            MB.ride(5.2);

            Truck_Type transporter = new Truck_Type("Volkswagen Transporter", 0.25, 120);
            transporter.ride(8);
            LOG.debug("Check all input data...");

            TaxiPark Yandex = new TaxiPark();
            Yandex.AddTaxi(polo);
            Yandex.AddTaxi(lancer);
            Yandex.AddTaxi(MB);
            Yandex.AddTaxi(transporter);
            System.out.println("\nМашины в таксопарке: ");
            for (Taxi t: Yandex.getTaxiList())
                System.out.println(t.getName());

            TaxiPark.Manager taxi_manager = Yandex.new Manager();
            String genProfit = String.format("%.2f", taxi_manager.getGeneralProfit());
            System.out.println("\nОбщая прибыль за прошедший час без вычета зарплаты: " + genProfit + " руб.");
            String companyProfit = String.format("%.2f", taxi_manager.getCompanyProfit());
            System.out.println("Общая прибыль за прошедший час с вычетом зарплаты:  " + companyProfit + " руб.");
            LOG.debug("Debug method getCompanyProfit() to understand it");
            taxi_manager.searchSpeed(170, 220);
            LOG.error("An error has occured with searchSpeed() method");
            taxi_manager.sortFuel();
            LOG.info("You successfully run this program!");
        }
        catch (Exception e)
        {
            LOG.fatal("Fatal error! " + e.getMessage());
        }
    }
}