package by.belstu.Lab3.Tests;

import by.belstu.Lab3.Cars.Car;
import by.belstu.Lab3.Cars.Taxi;
import by.belstu.Lab3.TaxiPark.TaxiPark;
import by.belstu.Lab3.TaxiTypes.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;


public class ManagerTest {
    private TaxiPark taxiPark;
    private TaxiPark.Manager manager;
    private Comfort_Type comfortType;
    private ComfortPlus_Type comfortPlusType;
    private Truck_Type truck1;
    private final double GENERAL_PROFIT_TAXIS = 20.62;
    @BeforeTest
    public void Init() {
        taxiPark = new TaxiPark();
        manager = taxiPark.new Manager();
        comfortType = new Comfort_Type("Mitsubishi Lancer", 0.13, 190);
        comfortPlusType = new ComfortPlus_Type("Mercedes-Benz E200", 0.15, 200);
        truck1 = new Truck_Type("Volkswagen Transporter", 0.25, 120);

        taxiPark.AddTaxi(comfortType);
        taxiPark.AddTaxi(comfortPlusType);
        taxiPark.AddTaxi(truck1);
    }
    @AfterTest
    public void Clear() {
        manager = null;
        taxiPark = null;
    }
    @BeforeMethod
    public void BeforeMethod() {
        System.out.println("Тест метода");
    }
    @AfterMethod
    public void AfterMethod() {
        System.out.println("Тест метода окончен");
    }
    @BeforeSuite
    public void BeforeSuite() {
        System.out.println("Запуск тестов");
    }
    @AfterSuite
    public void AfterSuite() {
        System.out.println("Тесты окончены");
    }

    @BeforeClass
    public void BeforeClass() {
        System.out.println("Тест класса");
    }

    @AfterClass
    public void AfterClass() {
        System.out.println("Тест класса окончен");
    }
    @Test
    public void TestGeneralProfit() {
        double a = manager.getGeneralProfit();
        Assert.assertEquals(a, GENERAL_PROFIT_TAXIS);
    }
    @Test
    public void TestSearchSpeed1() {
        ArrayList<Car> expected = new ArrayList<Car>();
        expected.add(comfortType);
        expected.add(comfortPlusType);
        ArrayList<Car> actual = manager.searchSpeed(180, 210);
        Assert.assertEquals(expected, actual);
    }
    @Test(timeOut = 1000)
    public void TestSearch() {
        ArrayList<Car> expected = new ArrayList<>();
        expected.add(comfortType);
        expected.add(comfortPlusType);
        ArrayList<Car> actual = manager.searchSpeed(180, 210);
        Assert.assertEquals(expected, actual);
    }

    @Ignore
    public void TestSearchSpeed2() {
        ArrayList<Car> expected = new ArrayList<>();
        expected.add(comfortType);
        ArrayList<Car> actual = manager.searchSpeed(180, 210);
        Assert.assertEquals(expected, actual);
    }

    @DataProvider(name = "testdata")
    public Object[] createData1() {
        TaxiPark company1 = new TaxiPark();
        ArrayList<Taxi> list1 = new ArrayList<>();
        list1.add(comfortType);
        list1.add(comfortPlusType);
        company1.setTaxiList(list1);
        TaxiPark company2 = new TaxiPark();
        ArrayList<Taxi> list2 = new ArrayList<>();
        list2.add(comfortType);
        list2.add(comfortPlusType);
        list2.add(truck1);
        company2.setTaxiList(list2);
        TaxiPark company3 = new TaxiPark();
        ArrayList<Taxi> list3 = new ArrayList<>();
        list3.add(truck1);
        company3.setTaxiList(list3);
        return new Object[] { 20.62, 20.62, 20.62};
    }
    @Test(dataProvider = "testdata")
    public void TestCount(double numoftaxis) {
        Assert.assertEquals(numoftaxis, manager.getGeneralProfit());
    }
}
