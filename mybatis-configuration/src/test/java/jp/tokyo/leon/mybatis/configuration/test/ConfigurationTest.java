package jp.tokyo.leon.mybatis.configuration.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;

/**
 * @author leon
 * @date 2024/2/2 00:45
 */
public class ConfigurationTest {

    @Test
    public void testEnvironment() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"), "test");

        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert("insertCar");
        sqlSession.commit();
        sqlSession.close();
    }
}
