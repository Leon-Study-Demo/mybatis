package jp.tokyo.leon.mybatis.dynamic.mapper;

import jp.tokyo.leon.mybatis.dynamic.entity.Car;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author leon
 * @date 2024/2/5 22:55
 */
public interface CarMapper {

    List<Car> selectByMultiCondition(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    List<Car> selectByMultiConditionWithWhere(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    List<Car> selectByMultiConditionWithTrim(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    int updateById(Car car);
    int updateByIdSet(Car car);




}
