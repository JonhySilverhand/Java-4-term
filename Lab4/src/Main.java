import Cars.Taxi;
import TaxiPark.TaxiPark;
import TaxiTypes.*;
import XML_Parser.StaxStreamProcessor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {
    static{
        new DOMConfigurator().doConfigure("log/log4j.xml",
                LogManager.getLoggerRepository());
    }
    private static final Logger LOG = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        try {
            LOG.info("Starting program_____________________________");

            Economy_Type polo = new Economy_Type();
            Comfort_Type lancer = new Comfort_Type();
            ComfortPlus_Type MB = new ComfortPlus_Type();
            Truck_Type transporter = new Truck_Type();

            List<Taxi> taxiList = Arrays.asList(polo, lancer, MB, transporter);

            ArrayList<String> taxiNames = new ArrayList<>();
            ArrayList<String> taxiSpeeds = new ArrayList<>();
            ArrayList<String> taxiFuels = new ArrayList<>();
            ArrayList<String> taxiTypes = new ArrayList<>();

            StaxStreamProcessor.addNamesToList("Name", taxiNames);
            StaxStreamProcessor.addNamesToList("Speed", taxiSpeeds);
            StaxStreamProcessor.addNamesToList("Fuel", taxiFuels);
            StaxStreamProcessor.addNamesToList("Type", taxiTypes);

            for (int i = 0; i < taxiList.size(); i++)
            {
                taxiList.get(i).setTaxi_name(taxiNames.get(i));
                taxiList.get(i).setTaxi_speed(Integer.parseInt(taxiSpeeds.get(i)));
                taxiList.get(i).setFuelRate(Double.parseDouble(taxiFuels.get(i)));
                taxiList.get(i).ride(Math.random() * 10);
            }

            System.out.println("\n\nПроиницализированные XML-документом машины:");
            for (Taxi taxi: taxiList)
                System.out.println("Название:  " + taxi.getTaxi_name() +
                                   "\nРасход:    " + taxi.getFuelRate() +
                                   " л/км\nСкорость:  " + taxi.getTaxi_speed() +
                                   " км/ч\nТип такси: " + taxi.getType_taxi() + "\n");


            TaxiPark Yandex = new TaxiPark();
            Yandex.setTaxiList(taxiList);
            System.out.println("\nМашины в таксопарке: ");
            for (Taxi t: Yandex.getTaxiList())
                System.out.println(t.getTaxi_name());

            TaxiPark.Manager taxi_manager = Yandex.new Manager();

            String genProfit = String.format("%.2f", taxi_manager.getGeneralProfit());
            System.out.println("\nОбщая прибыль за прошедший час без вычета зарплаты: " + genProfit + " BYN.");
            String companyProfit = String.format("%.2f", taxi_manager.getCompanyProfit());
            System.out.println("Общая прибыль за прошедший час с вычетом зарплаты:  " + companyProfit + " BYN.");

            taxi_manager.searchSpeed(170, 220);
            taxi_manager.sortFuel();

            File schemaFile = new File("files/validation.xsd");
            Source xmlFile = new StreamSource(new File("files/taxilist.xml"));
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
            System.out.println("\nXML-файл успешно прошёл валидацию с XSD!");


            try(FileWriter writer = new FileWriter("json/task4.json", false)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(Yandex.getTaxiList());
                writer.write(json);
                writer.flush();
            }
            catch (Exception e) {
                LOG.error(" io error " + e.getMessage());
            }

            try {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                Scanner scanner = new Scanner(new File("json/task4-1.json"), "utf-8");
                String result = "";
                while (scanner.hasNext()) {
                    result += scanner.nextLine();
                }
                scanner.close();
                var resultObj = gson.fromJson(result, Truck_Type.class);
                System.out.println("---------Deserialization-------------");
                System.out.println(resultObj.toString());
            }
            catch (IOException e) {
                LOG.error(" io error " + e.getMessage());
            }

            System.out.println("-------StreamAPI--------");
            var res = Yandex.getTaxiList().stream().filter(y -> y.getTaxi_speed() > 130).toArray();
            for (var y: res) {
                System.out.println(y.toString());
            }

            LOG.info("You successfully run this program!");
        }
        catch (Exception e)
        {
            LOG.fatal("Fatal error! " + e.getMessage());
        }
    }
}