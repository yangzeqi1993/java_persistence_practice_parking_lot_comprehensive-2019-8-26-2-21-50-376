package tws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tws.entity.ParkingLot;
import tws.service.ParkingLotService;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/parkinglots")
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    @GetMapping("")
    public ResponseEntity<List<ParkingLot>> getAllList() {
        return ResponseEntity.ok(parkingLotService.findAllParkingLots());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingLot> getOneParkingLot(@PathVariable int id) {
        return ResponseEntity.ok(parkingLotService.findOneParkingLot(id));
    }

    @PostMapping("")
    public ResponseEntity<ParkingLot> createEmployee(@RequestBody ParkingLot parkingLot) {
        parkingLotService.addOneParkingLot(parkingLot);
        return ResponseEntity.created(URI.create("/parkinglots/" + parkingLot.getId())).body(parkingLot);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingLot> updateParkingLot(@PathVariable int id, @RequestBody ParkingLot parkingLot){
        parkingLotService.updateOneParkingLot(id,parkingLot);
        return ResponseEntity.ok(parkingLot);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteParkingLot(@PathVariable int id) {
        parkingLotService.deleteOneParkingLot(id);
    }

}
