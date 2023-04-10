package by.belstu.Lab4.Task1;

public class Client extends Thread {
    Rental rental;

    Client(Rental rental){
        this.rental = rental;
    }
    public void run() {
        rental.get();
    }
}