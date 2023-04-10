package by.belstu.Lab4.Task2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
       Parking parking1 = new Parking(1,5);
       Parking parking2 = new Parking(2,7);
       Semaphore semaphore1 = new Semaphore(parking1.getSize(), true);
       Semaphore semaphore2 = new Semaphore(parking2.getSize(), true);
       ExecutorService executor = Executors.newCachedThreadPool();
       for (int i = 1; i <= 30; i++) {
           executor.execute(new Car(semaphore1, parking1, i++));
           executor.execute(new Car(semaphore2, parking2, i++));
       }
       executor.shutdown();
    }
}
