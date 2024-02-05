package jp.tokyo.leon.mybatis.web.dao;

import jp.tokyo.leon.mybatis.web.pojo.Account;

/**
 * @author leon
 * @date 2024/2/2 01:24
 */
public interface AccountDao {

    Account selectByActno(String actno);

    int updateByActno(Account account);
}
