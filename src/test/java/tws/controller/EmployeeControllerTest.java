package tws.controller;

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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import tws.entity.Employee;
import tws.entity.ParkingLot;

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

    @Test
    public void shouldGetAllEmployeeWhenGetEmployees() throws Exception {
        //given
        List<Employee> employees =  new ArrayList<>();
        employees.add(new Employee(1, "yang", "32"));
        employees.add(new Employee(2, "kathy", "22"));
        String postString = ObjectMapper.writeValueAsString(employees);
        //when
        this.mockMvc.perform(get("/employees"))

                //then
                .andDo(print()).andExpect(status().isOk())
                .andExpect(
                       content().json(postString)
                );
    }

        @Test
        public void shouldGetAllParkingLotsOneEmployeeWhenGetEmployees() throws Exception {
            //given
            List<ParkingLot> parkingLots =  new ArrayList<>();
            parkingLots.add(new ParkingLot(2, 8,8, 2));
            String postString = ObjectMapper.writeValueAsString(parkingLots);
            //when
            this.mockMvc.perform(get("/employees/2/parkinglots"))

                    //then
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(
                            content().json(postString)
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
                        .put("/employees/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postString)
        )
                //then
                .andDo(print()).andExpect(status().isOk())
                .andExpect(
                        content().json(postString)
                );
    }
}
