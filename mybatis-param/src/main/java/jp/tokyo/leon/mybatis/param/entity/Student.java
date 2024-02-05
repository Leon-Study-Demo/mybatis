package jp.tokyo.leon.mybatis.param.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author leon
 * @date 2024/2/5 22:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Long id;

    private String name;

    private Integer age;

    private Double height;

    private Date birth;

    private Character sex;
}
