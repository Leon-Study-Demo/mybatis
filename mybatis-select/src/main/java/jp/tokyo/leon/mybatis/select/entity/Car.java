package jp.tokyo.leon.mybatis.select.entity;

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
public class Car {
    private Integer id;

    private String carNum;

    private String brand;

    private Double guidePrice;

    private Date produceTime;

    private String carType;
}
