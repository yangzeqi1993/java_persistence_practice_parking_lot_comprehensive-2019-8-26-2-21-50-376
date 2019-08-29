package tws.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.http.ResponseEntity;
import tws.entity.Employee;
import tws.entity.ParkingLot;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Select("select * from employee;")
    List<Employee> selectAll();

    @Select("select * from employee where id = #{id};")
    Employee selectOne(@Param("id") int id);

    @Select("select * from parkinglot where parkingBoyId = #{id};")
    List<ParkingLot> selectOneParkingBoyAllParkingLots(@Param("id") int id);

    @Insert("insert into employee values (#{employee.id}, #{employee.name}, #{employee.age});")
    void insert(@Param("employee") Employee employee);

    @Update("update employee set employee.name=#{employee.name}, employee.age=#{employee.age} where employee.id = #{id};")
    void update(@Param("id") int id, @Param("employee") Employee employee);

    @Delete("delete from employee where employee.id = #{id};")
    void deleteOne(@Param("id") int id);

}
