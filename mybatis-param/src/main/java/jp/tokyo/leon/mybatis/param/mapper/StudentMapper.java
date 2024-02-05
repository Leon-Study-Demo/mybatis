package jp.tokyo.leon.mybatis.param.mapper;

import jp.tokyo.leon.mybatis.param.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author leon
 * @date 2024/2/5 22:55
 */
public interface StudentMapper {

    // 方法中的参数只有一个并且参数的类型都是简单类型

    List<Student> selectById(Long id);
    List<Student> selectByName(String name);
    List<Student> selectByBirth(Date birth);
    List<Student> selectBySex(Character sex);

    // 保存学生信息通过Map
    int insertStudentByMap(Map<String, Object> map);

    int insertStudentByEntity(Student student);

    /**
     *
     * 多个参数，mybatis是怎样做的呢？
     *      mybatis会自动创建一个map集合，并且map集合是以下面的方式存储参数的：
     *          map.put("arg0", name);
     *          map.put("arg1", sex);
     *          map.put("param1", name);
     *          map.put("param2", sex);
     */
    List<Student> selectByNameAndSex(String name, Character sex);
}
