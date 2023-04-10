package by.belstu.Lab4.Task2;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Car extends Thread {
    private final Semaphore park_slot;
    private final Parking parking;
    private final int id;

    public Car(Semaphore p_slot, Parking parking, int id) {
        this.park_slot = p_slot;
        this.parking = parking;
        this.id = id;
    }

    private void parking() {
        System.out.println("Car №" + id + " is parking slot №" + parking.getParkID());
    }
    private void leaving() {
        System.out.println("Car №" + id + " is leaving slot №" + parking.getParkID());
    }

    @Override
    public void run() {
        try{
            park_slot.acquire();
            parking();
            Thread.sleep(new Random().nextInt(200) + 190);
            park_slot.release();
            leaving();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
