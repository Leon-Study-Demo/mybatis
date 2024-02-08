package jp.tokyo.leon.mybatis.dynamic.test;

import jp.tokyo.leon.mybatis.dynamic.entity.Car;
import jp.tokyo.leon.mybatis.dynamic.mapper.CarMapper;
import jp.tokyo.leon.mybatis.dynamic.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author leon
 * @date 2024/2/6 22:12
 */
public class CarMapperTest {

    @Test
    public void selectByMultiCondition() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> carList = mapper.selectByMultiCondition("比亚迪", 2.0, "232");
        carList.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void selectByMultiConditionWithWhere() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> carList = mapper.selectByMultiConditionWithWhere("比亚迪", null, null);
        carList.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void selectByMultiConditionWithTrim() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> carList = mapper.selectByMultiConditionWithTrim("比亚迪", null, null);
        carList.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(29, "222", null, null, null, null);
        mapper.updateById(car);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdateByIdSet() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(29, null, null, 40.2, new Date(), null);
        mapper.updateByIdSet(car);
        sqlSession.commit();
        sqlSession.close();
    }

}
