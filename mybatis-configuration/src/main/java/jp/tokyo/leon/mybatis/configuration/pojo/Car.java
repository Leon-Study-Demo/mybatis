package jp.tokyo.leon.mybatis.configuration.pojo;

import lombok.*;

import java.util.Date;

/**
 * @author leon
 * @date 2024/1/30 23:26
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Car {
    /**
     * 为什么使用包装类？
     * 查询的时候字段可能为null，null无法给基本数据类型赋值，建议使用包装类型
     */

    private Integer id;

    private String carNum;

    private String brand;

    private Double guidePrice;

    private Date produceTime;

    private String carType;

}
