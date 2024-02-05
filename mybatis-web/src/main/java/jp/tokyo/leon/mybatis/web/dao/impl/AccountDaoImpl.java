package jp.tokyo.leon.mybatis.web.dao.impl;

import jp.tokyo.leon.mybatis.web.dao.AccountDao;
import jp.tokyo.leon.mybatis.web.pojo.Account;
import jp.tokyo.leon.mybatis.web.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * @author leon
 * @date 2024/2/2 01:26
 */
public class AccountDaoImpl implements AccountDao {
    @Override
    public Account selectByActno(String actno) {
        SqlSession session = SqlSessionUtil.openSession();
        return session.selectOne("account.selectByActno", actno);
    }

    @Override
    public int updateByActno(Account account) {
        SqlSession session = SqlSessionUtil.openSession();
        return session.update("account.updateByActno", account);
    }
}
