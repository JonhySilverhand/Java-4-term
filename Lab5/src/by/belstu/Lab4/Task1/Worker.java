package by.belstu.Lab4.Task1;

public class Worker extends Thread {
    Rental rental;
    Worker(Rental rental) {
        this.rental = rental;
    }
    public void run() {
        for(int i = 1; i < 6; i++){
            rental.put();
        }
    }
}
