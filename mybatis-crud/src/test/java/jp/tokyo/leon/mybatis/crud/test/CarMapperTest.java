package jp.tokyo.leon.mybatis.crud.test;

import jp.tokyo.leon.mybatis.crud.entity.Car;
import jp.tokyo.leon.mybatis.crud.uitls.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author leon
 * @date 2024/1/31 01:14
 */
public class CarMapperTest {

    @Test
    public void testInsertByUtil() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        int count = sqlSession.insert("insertCar");
        sqlSession.commit();
        sqlSession.close();
        Assert.assertEquals(1, count);
    }

    @Test
    public void testInsertCarPlaceholder() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        /*
         * insert 方法的参数：
         * 第一个参数：sqlId
         * 第二个参数：封装数据对象
         *
         * 对象先使用Map集合进行数据封装
         */

        Map<String, Object> map = new HashMap<>();
        map.put("k1", "1111");
        map.put("k2", "比亚迪汉");
        map.put("k3", 10.0);
        map.put("k4", "2020-11-11");
        map.put("k5", "电车");
        int count = sqlSession.insert("insertCarPlaceholder", map);
        sqlSession.commit();
        sqlSession.close();
        Assert.assertEquals(1, count);
    }

    @Test
    public void testInsertCarPlaceholder1() {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        Map<String, Object> map = new HashMap<>();
        map.put("carNum", "1111");
        map.put("brand", "比亚迪汉");
        map.put("guidePrice", 10.0);
        map.put("produceTime", "2020-11-11");
        map.put("carType", "电车");
        int count = sqlSession.insert("insertCarPlaceholder1", map);
        sqlSession.commit();
        sqlSession.close();
        Assert.assertEquals(1, count);
    }

    @Test
    public void testInsertCarByEntity() {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        Car car = Car.builder()
                .carNum("2222")
                .brand("特斯拉")
                .guidePrice(20D)
                .produceTime(new Date())
                .carType("电车")
                .build();
        int count = sqlSession.insert("insertCarByEntity", car);
        sqlSession.commit();
        sqlSession.close();
        Assert.assertEquals(1, count);
    }

    @Test
    public void testDeleteById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        int count = sqlSession.delete("deleteById", 10);
        sqlSession.commit();
        sqlSession.close();
        Assert.assertEquals(1, count);
    }


    @Test
    public void testUpdateById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Car car = Car.builder()
                .id(8)
                .carNum("2222")
                .brand("特斯拉")
                .guidePrice(20D)
                .produceTime(new Date())
                .carType("电车")
                .build();
        int count = sqlSession.update("updateById", car);
        sqlSession.commit();
        sqlSession.close();
        Assert.assertEquals(1, count);
    }

    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        // 执行DQL语句。查询。根据id查询，返回结果一定是一条
        // mybatis底层执行select语句之后，一定返回一个结果集对象：ResultSet
        // JDBC中叫做ResultSet，接下来Mybatis应该从ResultSet中取出数据，封装java对象
        Car car = sqlSession.selectOne("selectById", 2);
        System.out.println(car);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        // 执行DQL语句。查询。根据id查询，返回结果一定是一条
        // mybatis底层执行select语句之后，一定返回一个结果集对象：ResultSet
        // JDBC中叫做ResultSet，接下来Mybatis应该从ResultSet中取出数据，封装java对象
        List<Car> cars = sqlSession.selectList("selectAll");
        cars.forEach(System.out::println);
        sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void testNameSpace() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        // 正确完整的写法：namespace + id
        List<Car> cars = sqlSession.selectList("jp.tokyo.leon.mybatis.entity.User.selectAll");
        cars.forEach(System.out::println);
        sqlSession.commit();
        sqlSession.close();
    }


}
