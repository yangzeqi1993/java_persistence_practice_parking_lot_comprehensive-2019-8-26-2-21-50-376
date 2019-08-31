package tws.repository;

import org.apache.ibatis.annotations.Param;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import tws.entity.Employee;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@MybatisTest

public class EmployeeMapperTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Before
    public void tearDown() throws Exception {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "employee");
    }

    @Test
    public void shouldFetchAllEmployeesWhenSelectAll() {
        // given

        jdbcTemplate.execute("INSERT INTO EMPLOYEE VALUES(1,'zhangsan', '21');");
        // when
        List<Employee> employeeList = employeeMapper.selectAll();
        // then
        assertEquals(1, employeeList.size());
    }

    @Test
    public void shouldFetchOneEmployeeWhenSelectOne() {
        // given

        jdbcTemplate.execute("INSERT INTO EMPLOYEE VALUES(1,'zhangsan', '21');");
        // when
        Employee employee = employeeMapper.selectOne(1);
        // then
        assertEquals(1, employee.getId());
        assertEquals("zhangsan", employee.getName());
        assertEquals("21", employee.getAge());
    }

    @Test
    public void shouldCreateOneEmployeeWhenInsert() {
        // given
        Employee employee = new Employee(1,"zhangsan", "21");
        // when
        employeeMapper.insert(employee);
        Employee employeeInsert = employeeMapper.selectOne(1);
        // then
        assertEquals(employee.getId(), employeeInsert.getId());
        assertEquals(employee.getName(), employeeInsert.getName());
        assertEquals(employee.getAge(), employeeInsert.getAge());
    }

    @Test
    public void shouldUpdateOneEmployeeWhenUpdate() {
        // given
        Employee employee = new Employee(1,"Yang", "25");
        jdbcTemplate.execute("INSERT INTO EMPLOYEE VALUES(1,'zhangsan', '21');");
        // when
        employeeMapper.update(1, employee);
        Employee employeeInsert = employeeMapper.selectOne(1);
        // then
        assertEquals(employee.getName(), employeeInsert.getName());
        assertEquals(employee.getAge(), employeeInsert.getAge());
    }

    @Test
    public void shouldDeleteOneEmployee() {
        // given
        jdbcTemplate.execute("INSERT INTO EMPLOYEE VALUES(1,'zhangsan', 21);");
        jdbcTemplate.execute("INSERT INTO EMPLOYEE VALUES(2, 'kathy', 22);");
        // when
        employeeMapper.deleteOne(1);
        Employee employee = employeeMapper.selectOne(1);
        // then
        assertNull(employee);
    }




}
