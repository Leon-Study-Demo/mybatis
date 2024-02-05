package jp.tokyo.leon.mybatis.test;

import jp.tokyo.leon.mybatis.entity.Car;
import jp.tokyo.leon.mybatis.entity.Log;
import jp.tokyo.leon.mybatis.mapper.CarMapper;
import jp.tokyo.leon.mybatis.mapper.LogMapper;
import jp.tokyo.leon.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author leon
 * @date 2024/2/5 21:39
 */
public class LogMapperTest {

    @Test
    public void testSelectAllByTable() {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        LogMapper logMapper = sqlSession.getMapper(LogMapper.class);

        List<Log> logs = logMapper.selectAllByTable("20220901");
        logs.forEach(System.out::println);

        sqlSession.close();
    }
}
