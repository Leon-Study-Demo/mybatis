package jp.tokyo.leon.mybatis.select.mapper;


import jp.tokyo.leon.mybatis.select.entity.Car;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * @author leon
 * @date 2024/2/5 22:55
 */
public interface CarMapper {

    List<Car> selectAllByResultMap();
    @MapKey("id")
    Map<Long, Map<String, Object>> selectAllRetBigMap();

    Map<String, Object> selectByIdRetMap(Long id);

    List<Map<String, Object>> selectAllRetMap();

    Car selectById(Long id);

    List<Car> selectAll();

    Car selectByBrandLike(String brand);

    Long selectTotal();

}
