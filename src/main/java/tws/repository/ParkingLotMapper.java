package tws.repository;

import org.apache.ibatis.annotations.*;
import tws.entity.ParkingLot;

import java.util.List;

@Mapper
public interface ParkingLotMapper {

    @Select("select * from parkinglot;")
    List<ParkingLot> selectAll();

    @Insert("insert into parkinglot values (#{parkinglot.id}, #{parkinglot.capacity}, #{parkinglot.availablePositionCount}, #{parkinglot.parkingBoysId});")
    void insert(@Param("parkinglot") ParkingLot parkinglot);

    @Update("update parkinglot set parkinglot.capacity=#{employee.capacity}, parkinglot.availablePositionCount=#{employee.availablePositionCount} where parkinglot.parkingBoyId = #{parkingBoyId};")
    void update(@Param("parkinglot") int parkingBoyId, @Param("employee") ParkingLot parkinglot);

    @Delete("delete from parkinglot where parkinglot.parkingboy_id #{parkingBoysId};")
    void deleteOne(@Param("parkingBoysId") int parkingBoysId);
}
