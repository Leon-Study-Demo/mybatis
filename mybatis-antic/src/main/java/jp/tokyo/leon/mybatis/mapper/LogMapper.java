package jp.tokyo.leon.mybatis.mapper;

import jp.tokyo.leon.mybatis.entity.Log;

import java.util.List;

/**
 * @author leon
 * @date 2024/2/5 21:38
 */
public interface LogMapper {

    List<Log> selectAllByTable(String time);
}
