package by.belstu.Lab4.Task1;

public class Retiree extends Thread {
    Rental rental;

    Retiree(Rental rental){
        this.rental = rental;
    }
    public void run(){
        rental.getRetiree();
    }
}
