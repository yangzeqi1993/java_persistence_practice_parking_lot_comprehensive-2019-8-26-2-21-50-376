package tws.entity;

public class ParkingLot {
    private int id;
    private int capacity;
    private int availablePositionCount;
    private int parkingBoyId;

    public ParkingLot(){};

    public ParkingLot(int id, int capacity, int availablePositionCount, int parkingBoyId){
        this.id = id;
        this.capacity = capacity;
        this.availablePositionCount = availablePositionCount;
        this.parkingBoyId = parkingBoyId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setAvailablePositionCount(int availablePositionCount) {
        this.availablePositionCount = availablePositionCount;
    }

    public int getAvailablePositionCount() {
        return availablePositionCount;
    }

    public void setParkingBoyId(int parkingBoyId) {
        this.parkingBoyId = parkingBoyId;
    }

    public int getParkingBoyId() {
        return parkingBoyId;
    }
}
