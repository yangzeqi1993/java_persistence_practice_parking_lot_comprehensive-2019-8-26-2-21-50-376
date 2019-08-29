package tws.repository;

import org.apache.ibatis.annotations.*;
import tws.entity.Employee;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Select("select * from employee;")
    List<Employee> selectAll();

    @Insert("insert into employee values (#{employee.id}, #{employee.name}, #{employee.age});")
   void insert(@Param("employee") Employee employee);

    @Update("update employee set employee.name=#{employee.name}, employee.age=#{employee.age} where employee.id = #{id};")
   void update(@Param("employee") int id, @Param("employee") Employee employee);

    @Delete("delete from employee where employee.id = #{id};")
    void deleteOne(@Param("id") int id);

}
