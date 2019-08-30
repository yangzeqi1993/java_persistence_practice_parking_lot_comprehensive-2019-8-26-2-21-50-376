package tws.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.hamcrest.CoreMatchers;
import org.junit.Test;
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
    JdbcTemplate jdbcParkingLot;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcParkingLot = new JdbcTemplate(dataSource);
    }

    @Before
    public void tearDown() throws Exception {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "employee");
        JdbcTestUtils.deleteFromTables(jdbcParkingLot, "parkinglot");
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
        public void shouldGetAllParkingLotsOneEmployeeWhenGetEmployees() throws Exception {
            //given
            jdbcTemplate.execute("INSERT INTO EMPLOYEE VALUES(1,'yang', '21');");
            jdbcTemplate.execute("INSERT INTO EMPLOYEE VALUES(2,'zeqi', '25');");
            jdbcParkingLot.execute("INSERT INTO parkinglot VALUES(1 ,5 ,5 ,2);");
            jdbcParkingLot.execute("INSERT INTO PARKINGLOT VALUES(2 ,6 ,2 ,1);");
            jdbcParkingLot.execute("INSERT INTO PARKINGLOT VALUES(3 ,8 ,8 ,2);");
            List<ParkingLot> parkingLots =  new ArrayList<>();
            parkingLots.add(new ParkingLot(1, 5,5, 2));
            parkingLots.add(new ParkingLot(3, 8,8, 2));
            String getString = ObjectMapper.writeValueAsString(parkingLots);

            //when
            this.mockMvc.perform(
                    get("/employees/2/parkinglots"))
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
        String postString = ObjectMapper.writeValueAsString(employee);
        //when
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/employees/{id}",4)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postString)
        )
                //then
                .andDo(print()).andExpect(status().isOk())
                .andExpect(
                        content().json(postString)
                );
    }

    @Test
    public void shouldDeleteOneEmployeeWhenDeleteEmployees() throws Exception {
        //given

        //when
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/employees/{id}",2)
        )
                //then
                .andDo(print()).andExpect(status().isOk());
    }
}
