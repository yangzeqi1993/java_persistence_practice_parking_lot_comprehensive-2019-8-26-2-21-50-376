package tws.repository;

import org.apache.ibatis.annotations.*;
import tws.entity.ParkingLot;

import java.util.List;

@Mapper
public interface ParkingLotMapper {

    @Select("select * from parkinglot;")
    List<ParkingLot> selectAll();

    @Select("select * from parkinglot where where id = #{id};")
    ParkingLot selectOne(@Param("id") int id);

    @Insert("insert into parkinglot values (#{parkinglot.id}, #{parkinglot.capacity}, #{parkinglot.availablePositionCount}, #{parkinglot.parkingBoyId});")
    void insert(@Param("parkinglot") ParkingLot parkinglot);

    @Update("update parkinglot set parkinglot.capacity=#{parkinglot.capacity}, parkinglot.availablePositionCount=#{parkinglot.availablePositionCount} where parkinglot.parkingBoyId = #{parkingBoyId};")
    void update(@Param("parkingBoyId") int parkingBoyId, @Param("parkinglot") ParkingLot parkinglot);

    @Delete("delete from parkinglot where id = #{id};")
    void deleteOne(@Param("id") int id);
}
