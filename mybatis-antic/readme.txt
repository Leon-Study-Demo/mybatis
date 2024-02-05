mybatis小技巧
1. #{} 和 ${}的区别

#{}
2024-02-05 21:15:19.467 [main] DEBUG j.t.leon.mybatis.mapper.CarMapper.selectByCarType - ==>  Preparing: select id, car_num as carNum, brand, guide_price as guidePrice, produce_time as produceTime, car_type as carType from t_car where car_type = ?
2024-02-05 21:15:19.483 [main] DEBUG j.t.leon.mybatis.mapper.CarMapper.selectByCarType - ==> Parameters: 燃油车(String)
2024-02-05 21:15:19.508 [main] DEBUG j.t.leon.mybatis.mapper.CarMapper.selectByCarType - <==      Total: 11

${}

2024-02-05 21:16:44.375 [main] DEBUG j.t.leon.mybatis.mapper.CarMapper.selectByCarType - ==>  Preparing: select id, car_num as carNum, brand, guide_price as guidePrice, produce_time as produceTime, car_type as carType from t_car where car_type = 燃油车
2024-02-05 21:16:44.392 [main] DEBUG j.t.leon.mybatis.mapper.CarMapper.selectByCarType - ==> Parameters:

### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column '燃油车' in 'where clause'
### The error may exist in CarMapper.xml
### The error may involve defaultParameterMap
### The error occurred while setting parameters
### SQL: select         id,         car_num as carNum,         brand,         guide_price as guidePrice,         produce_time as produceTime,         car_type as carType         from t_car         where car_type = 燃油车
### Cause: java.sql.SQLSyntaxErrorException: Unknown column '燃油车' in 'where clause'

#{} 和 ${} 的区别

 #{}: 底层使用PreparedStatement。特点：先进行SQL语句的编译，然后给SQL语句的占位符?传值。可以避免SQL注入。
 ${}: 底层使用Statement。特点：先进行SQL语句的拼接，然后再对SQL语句进行编译。存在SQL注入的风险。


#{}执行结果
 Preparing: select id, car_num as carNum, brand, guide_price as guidePrice, produce_time as produceTime, car_type as carType from t_car where car_type = asc order by produce_time ?
 Parameters: asc(String)

 select
 id, car_num as carNum,
 brand, guide_price as guidePrice,
 produce_time as produceTime,
 car_type as carType
 from t_car
 order by produce_time 'asc'

 ${}执行结果
Preparing: select id, car_num as carNum, brand, guide_price as guidePrice, produce_time as produceTime, car_type as carType from t_car order by produce_time asc

如果需要SQL语句的关键字放入SQL语句中，只能使用${}

2. 向SQL语句中拼接表名，就需要${}
    现实业务中，可能会存在分表存储数据的情况。因为一张表存的话，数据量太大，查询效率变低。
    可以将这些数据分表存储，这样查询的效率比较高。
    比如日志表
        t_log_20220901


