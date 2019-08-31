package tws.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import tws.entity.ParkingLot;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@MybatisTest
public class ParkingLotMapperTest {

    @Autowired
    private ParkingLotMapper parkingLotMapper;

    JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Before
    public void tearDown() throws Exception {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "parkinglot");
    }

    @Test
    public void shouldFetchAllParkingLotsWhenSelectAll() {
        // given
        jdbcTemplate.execute("INSERT INTO parkinglot VALUES(1, 10, 5, 2);");

        // when
        List<ParkingLot> parkingLots = parkingLotMapper.selectAll();

        // then
        assertEquals(1, parkingLots.size());
        assertEquals(1, parkingLots.get(0).getId());
        assertEquals(10, parkingLots.get(0).getCapacity());
        assertEquals(5, parkingLots.get(0).getAvailablePositionCount());
        assertEquals(2, parkingLots.get(0).getParkingBoyId());
    }

    @Test
    public void shouldFetchOneParkingLotWhenSelectOne() {
        // given
        jdbcTemplate.execute("INSERT INTO parkinglot VALUES(1, 10, 5, 2);");
        int id = 1;

        // when
        ParkingLot parkingLot = parkingLotMapper.selectOne(id);

        // then
        assertEquals(1, parkingLot.getId());
        assertEquals(10, parkingLot.getCapacity());
        assertEquals(5, parkingLot.getAvailablePositionCount());
        assertEquals(2, parkingLot.getParkingBoyId());
    }

    @Test
    public void shouldCreateOneParkingLotWhenInsert() {
        // given
        ParkingLot parkingLot = new ParkingLot(1, 10, 5, 2);

        // when
        parkingLotMapper.insert(parkingLot);

        // then
        ParkingLot parkingLotInsert = parkingLotMapper.selectOne(1);
        assertEquals(parkingLot.getId(), parkingLotInsert.getId());
        assertEquals(parkingLot.getCapacity(), parkingLotInsert.getCapacity());
        assertEquals(parkingLot.getAvailablePositionCount(), parkingLotInsert.getAvailablePositionCount());
        assertEquals(parkingLot.getParkingBoyId(), parkingLotInsert.getParkingBoyId());
    }

    @Test
    public void shouldUpdateOneParkingLotWhenUpdate() {
        // given
        ParkingLot parkingLot = new ParkingLot(1, 10, 5, 2);
        jdbcTemplate.execute("INSERT INTO parkinglot VALUES(1, 10, 5, 2);");

        // when
        parkingLotMapper.update(1, parkingLot);

        // then
        ParkingLot parkingLotUpdate = parkingLotMapper.selectOne(1);
        assertEquals(parkingLot.getId(), parkingLotUpdate.getId());
        assertEquals(parkingLot.getCapacity(), parkingLotUpdate.getCapacity());
        assertEquals(parkingLot.getAvailablePositionCount(), parkingLotUpdate.getAvailablePositionCount());
        assertEquals(parkingLot.getParkingBoyId(), parkingLotUpdate.getParkingBoyId());
    }

    @Test
    public void shouldDeleteOneParkingLot() {
        // given
        jdbcTemplate.execute("INSERT INTO parkinglot VALUES(1, 10, 5, 2);");

        // when
        parkingLotMapper.deleteOne(1);

        // then
        ParkingLot parkingLot = parkingLotMapper.selectOne(1);
        assertNull(parkingLot);
    }


}
