package jp.tokyo.leon.mybatis.test;

import jp.tokyo.leon.mybatis.entity.Car;
import jp.tokyo.leon.mybatis.mapper.CarMapper;
import jp.tokyo.leon.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author leon
 * @date 2024/2/5 20:56
 */
public class CarMapperTest {

    @Test
    public void testSelectCarType() {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        CarMapper mapper = sqlSession.getMapper(CarMapper.class);

        List<Car> cars = mapper.selectByCarType("燃油车");
        cars.forEach(System.out::println);

        sqlSession.close();
    }

    @Test
    public void testSelectAllByOrder() {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        CarMapper mapper = sqlSession.getMapper(CarMapper.class);

        List<Car> cars = mapper.selectAllByOrder("asc");
        cars.forEach(System.out::println);

        sqlSession.close();
    }

    @Test
    public void testInsertCarUseGeneratedKeys() {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        CarMapper mapper = sqlSession.getMapper(CarMapper.class);

        Car car = new Car(null, "9991", "dsada", 30.0, new Date(), "燃油车");

        mapper.insertCarUseGeneratedKeys(car);

        System.out.println(car);

        sqlSession.commit();

        sqlSession.close();
    }
}
