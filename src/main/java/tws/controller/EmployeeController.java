package tws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tws.entity.Employee;
import tws.entity.ParkingLot;
import tws.repository.EmployeeMapper;
import tws.service.EmployeeService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public ResponseEntity<List<Employee>> getAllInOnePage(@RequestParam (name="pageNum", required = false) Integer pageNum,
                                                          @RequestParam(name="pageSize", required = false) Integer pageSize)
    {
        if(pageNum != null && pageSize != null){
            return ResponseEntity.ok(employeeService.findAllEmployeesInPagesBySql(pageNum,pageSize));
        } else {
            return ResponseEntity.ok(employeeService.findAllEmployees());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getOne(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.findOneEmployee(id));
    }

    @GetMapping("/{id}/parkinglots")
    public ResponseEntity<List<ParkingLot>> getOneParkingBoyAllParkingLots(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.FindOneParkingBoyAllParkingLots(id));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        employeeService.addOneEmployee(employee);
        return ResponseEntity.created(URI.create("/employees/" + employee.getId())).body(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employee){
        employeeService.updateOneEmployee(id,employee);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteOneEmployee(id);
    }
}
