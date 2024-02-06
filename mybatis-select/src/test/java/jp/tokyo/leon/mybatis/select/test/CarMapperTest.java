package jp.tokyo.leon.mybatis.select.test;

import jp.tokyo.leon.mybatis.select.entity.Car;
import jp.tokyo.leon.mybatis.select.mapper.CarMapper;
import jp.tokyo.leon.mybatis.select.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author leon
 * @date 2024/2/6 22:12
 */
public class CarMapperTest {

    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = mapper.selectById(3L);
        System.out.println(car);
        sqlSession.close();
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAll();
        cars.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectByBrandLike() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = mapper.selectByBrandLike("比亚迪汉");
        System.out.println(car);
        sqlSession.close();
    }

    @Test
    public void testSelectByIdRetMap() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Map<String, Object> map = mapper.selectByIdRetMap(3L);
        System.out.println(map);
        sqlSession.close();
    }

    @Test
    public void testSelectAllRetMap() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Map<String, Object>> maps = mapper.selectAllRetMap();
        maps.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectAllRetBigMap() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Map<Long, Map<String, Object>> map = mapper.selectAllRetBigMap();
        System.out.println(map);
        sqlSession.close();
    }

    @Test
    public void testSelectAllByResultMap() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAllByResultMap();
        cars.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectTotal() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Long num = mapper.selectTotal();
        System.out.println(num);
        sqlSession.close();
    }

}
