package jp.tokyo.leon.mybatis.web.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * @author leon
 * @date 2024/1/31 01:05
 */
public class SqlSessionUtil {

    // 工具类的构造方法一般都是私有化的
    // 工具类的所有方法都是静态的
    // 防止new对象，构造私有化
    private SqlSessionUtil(){}

    private static final SqlSessionFactory SQL_SESSION_FACTORY;

    static {
        try {
            SQL_SESSION_FACTORY = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final ThreadLocal<SqlSession> local = new ThreadLocal<>();

    public static SqlSession openSession() {
        SqlSession session = local.get();
        if (session == null) {
            session = SQL_SESSION_FACTORY.openSession();
            local.set(session);
        }
        return session;
    }

    public static void close(SqlSession session) {
        if (session != null) {
            session.close();
        }
        local.remove();
    }
}
