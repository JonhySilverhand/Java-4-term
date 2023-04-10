package by.belstu.Lab4.Task2;

public class Parking extends Thread {
    private final int parkID;
    private final int size;

    public Parking(int ID, int size) {
        this.parkID = ID;
        this.size = size;
    }

    public int getParkID() {
        return parkID;
    }

    public int getSize() {
        return size;
    }
}
