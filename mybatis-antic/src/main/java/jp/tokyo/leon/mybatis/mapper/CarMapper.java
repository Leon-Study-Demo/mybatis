package jp.tokyo.leon.mybatis.mapper;

import jp.tokyo.leon.mybatis.entity.Car;

import java.util.List;

/**
 * @author leon
 * @date 2024/2/5 20:55
 */
public interface CarMapper {

    List<Car> selectByCarType(String carType);

    List<Car> selectAllByOrder(String order);

    int insertCarUseGeneratedKeys(Car car);

}
