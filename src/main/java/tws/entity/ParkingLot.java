package tws.entity;

public class ParkingLot {
    private int id;
    private int capacity;
    private int availablePositionCount;
    private int parkingBoysId;

    public ParkingLot(){};

    public ParkingLot(int id, int capacity, int availablePositionCount, int parkingBoysId){
        this.id = id;
        this.capacity = capacity;
        this.availablePositionCount = availablePositionCount;
        this.parkingBoysId = parkingBoysId;
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

    public void setParkingBoysId(int parkingBoysId) {
        this.parkingBoysId = parkingBoysId;
    }

    public int getParkingBoysId() {
        return parkingBoysId;
    }
}
