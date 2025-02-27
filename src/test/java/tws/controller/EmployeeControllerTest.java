package tws.controller;

import org.junit.Before;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import tws.entity.Employee;
import tws.entity.ParkingLot;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper ObjectMapper;

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
    public void shouldGetAllEmployeesWhenGetEmployees() throws Exception {
        //given
        jdbcTemplate.execute("INSERT INTO EMPLOYEE VALUES(1,'yang', '21');");
        jdbcTemplate.execute("INSERT INTO EMPLOYEE VALUES(2,'zeqi', '25');");
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"yang","21"));
        employees.add(new Employee(2,"zeqi","25"));
        String getString = ObjectMapper.writeValueAsString(employees);

        //when
        this.mockMvc.perform(
                       get("/employees")
        )

        //then
                .andDo(print()).andExpect(status().isOk())
                .andExpect(
                       content().json(getString)
                );
    }

    @Test
    public void shouldReturnNotFoundWhenGetErrorUrl() throws Exception {
        //given

        //when
        this.mockMvc.perform(
                get("/employee")
        )
                //then
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void shouldGetEmployeesInOnePageWhenGetEmployees() throws Exception {
        //given
        jdbcTemplate.execute("INSERT INTO EMPLOYEE VALUES(1,'yang', '21');");
        jdbcTemplate.execute("INSERT INTO EMPLOYEE VALUES(2,'zeqi', '25');");
        jdbcTemplate.execute("INSERT INTO EMPLOYEE VALUES(3,'yang', '21');");
        jdbcTemplate.execute("INSERT INTO EMPLOYEE VALUES(4,'zeqi', '25');");
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(3,"yang","21"));
        employees.add(new Employee(4,"zeqi","25"));
        String getString = ObjectMapper.writeValueAsString(employees);

        //when
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/employees")
                        .param("pageNum","2")
                        .param("pageSize","2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getString)
        )

        //then
                .andDo(print()).andExpect(status().isOk())
                .andExpect(
                        content().json(getString)
                );
    }

    @Test
    public void shouldGetOneEmployeeWhenGetEmployee() throws Exception {
        //given
        jdbcTemplate.execute("INSERT INTO EMPLOYEE VALUES(2,'zeqi', '25');");
        Employee employee = new Employee(2,"zeqi","25");
        String getString = ObjectMapper.writeValueAsString(employee);

        //when
        this.mockMvc.perform(
                get("/employees/{id}",2)
        )

        //then
                .andDo(print()).andExpect(status().isOk())
                .andExpect(
                        content().json(getString)
                );
    }

    @Test
    public void shouldAddOneEmployeeWhenPostEmployees() throws Exception {
        //given
        Employee employee =  new Employee(4,"aaa","33");
        String postString = ObjectMapper.writeValueAsString(employee);
        //when
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postString)
        )
                //then
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(
                        content().json(postString)
                );
    }

    @Test
    public void shouldUpdateOneEmployeeWhenPutEmployees() throws Exception {
        //given
        Employee employee =  new Employee(4,"aaa","33");
        String putString = ObjectMapper.writeValueAsString(employee);
        //when
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/employees/{id}",4)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(putString)
        )
                //then
                .andDo(print()).andExpect(status().isOk())
                .andExpect(
                        content().json(putString)
                );
    }

    @Test
    public void shouldDeleteOneEmployeeWhenDeleteEmployees() throws Exception {
        //given
        int id = 2;
        //when
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/employees/{id}",id)
        )
                //then
                .andDo(print()).andExpect(status().isOk());
    }


    @Test
    public void shouldReturnMethodNotAllowedWhenDeleteErrorUrl() throws Exception {
        //given

        //when
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/employees/")
        )
                //then
                .andDo(print()).andExpect(status().isMethodNotAllowed());
    }
}
