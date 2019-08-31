package tws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tws.entity.ParkingLot;
import tws.repository.ParkingLotMapper;

import java.util.List;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotMapper parkingLotMapper;

    public List<ParkingLot> findAllParkingLots(){
        return parkingLotMapper.selectAll();
    }

    public ParkingLot findOneParkingLot(int id){
        return parkingLotMapper.selectOne(id);
    }

    public void addOneParkingLot(ParkingLot parkingLot){
        parkingLotMapper.insert(parkingLot);
    }

    public void updateOneParkingLot(int id, ParkingLot parkingLot){
        parkingLotMapper.update(id,parkingLot);
    }

    public void deleteOneParkingLot(int id) {
        parkingLotMapper.deleteOne(id);
    }

}
