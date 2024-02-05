package jp.tokyo.leon.mybatis.web.service.impl;

import jp.tokyo.leon.mybatis.web.dao.AccountDao;
import jp.tokyo.leon.mybatis.web.dao.impl.AccountDaoImpl;
import jp.tokyo.leon.mybatis.web.exceptions.MoneyNotEnoughException;
import jp.tokyo.leon.mybatis.web.exceptions.TransferException;
import jp.tokyo.leon.mybatis.web.pojo.Account;
import jp.tokyo.leon.mybatis.web.service.AccountService;
import jp.tokyo.leon.mybatis.web.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * @author leon
 * @date 2024/2/2 01:21
 */
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao = new AccountDaoImpl();
    @Override
    public void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, TransferException {

        SqlSession session = SqlSessionUtil.openSession();
        Account fromAct = accountDao.selectByActno(fromActno);
        if (fromAct.getBalance() < money) {
            throw new MoneyNotEnoughException("对不起余额不足");
        }

        Account toAct = accountDao.selectByActno(toActno);

        fromAct.setBalance(fromAct.getBalance() - money);
        toAct.setBalance(toAct.getBalance() + money);

        int count = accountDao.updateByActno(fromAct);
        // 模拟异常
        String s = null;
        s.toString();
        count += accountDao.updateByActno(toAct);
        if (count != 2) {
            throw new TransferException("转账异常，原因未知");
        }
        session.commit();
        SqlSessionUtil.close(session);
    }
}
