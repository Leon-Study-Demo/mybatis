package jp.tokyo.leon.mybatis.param.test;

import jp.tokyo.leon.mybatis.param.entity.Student;
import jp.tokyo.leon.mybatis.param.mapper.StudentMapper;
import jp.tokyo.leon.mybatis.param.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

/**
 * @author leon
 * @date 2024/2/5 22:57
 */
public class StudentMapperTest {

    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> studentList = mapper.selectById(1L);
        sqlSession.close();
        studentList.forEach(System.out::println);
    }

    @Test
    public void testSelectByName() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> studentList = mapper.selectByName("李四");
        sqlSession.close();
        studentList.forEach(System.out::println);
    }

    /**
     *
     *  日期这里需要注意：
     *  java的Date 对应 jdbc的好几个类型
     *  比如java的Date可以对应 timestamp date 和datetime等
     *  假如只查寻日期，java 传值 '2023-03-22 00:00:00' 则可以匹配
     *  但是如果传 '2023-03-22 18:00:00'则不能匹配，这时如果指定jdbcType=DATE,则查询时会自动截断
     *  对于一个java类型对应好几个jdbcType的情况，指定参数类型比较好
     */
    @Test
    public void testSelectByBirth() throws ParseException {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        Date birth = simpleDateFormat.parse("1980-02-05 18");
        List<Student> studentList = mapper.selectByBirth(birth);
        sqlSession.close();
        studentList.forEach(System.out::println);
    }

    @Test
    public void testSelectBySex() throws ParseException {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> studentList = mapper.selectBySex('男');
        sqlSession.close();
        studentList.forEach(System.out::println);
    }

    @Test
    public void testInsertStudentByMap() throws ParseException {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("姓名", "赵六");
        map.put("年龄", 20);
        map.put("身高", 1.81);
        map.put("性别", "男");
        map.put("生日", new Date());
        mapper.insertStudentByMap(map);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertStudentByEntity() throws ParseException {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = new Student();
        student.setName("张飞");
        student.setAge(20);
        student.setBirth(new Date());
        student.setHeight(1.89);
        student.setSex('女');
        mapper.insertStudentByEntity(student);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectByNameAndSex() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> studentList = mapper.selectByNameAndSex("张三", '男');
        studentList.forEach(System.out::println);
        sqlSession.close();
    }
}
