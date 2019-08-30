package tws.service;

import org.h2.util.New;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tws.entity.Employee;
import tws.repository.EmployeeMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> findAllEmployees(){
        return employeeMapper.selectAll();
    }

    public List<Employee> findAllEmployeesInPages(int pageNum, int pageSize){
        List<Employee> employees= employeeMapper.selectAll();
        int displayNum = (pageNum-1) * pageSize;

        List<Employee> employeesInOnePage = new ArrayList<>();
        for(int i=displayNum; i < employees.size() && i <displayNum+pageSize; i++){
            employeesInOnePage.add(employees.get(i));
        }
        return employeesInOnePage;
    }

    public List<Employee> findAllEmployeesInPagesBySql(int pageNum, int pageSize){
        int startNum = (pageNum-1) * pageSize;
        return employeeMapper.selectAllInOnePageBySql(startNum,pageSize);
    }












}
