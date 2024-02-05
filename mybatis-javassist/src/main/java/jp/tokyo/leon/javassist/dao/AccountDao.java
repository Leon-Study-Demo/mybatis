package jp.tokyo.leon.javassist.dao;

/**
 * @author leon
 * @date 2024/2/5 10:49
 */
public interface AccountDao {
    void delete();

    int insert(String actno);

    int update(String actno, Double balance);

    String selectByActno(String actno);
}
