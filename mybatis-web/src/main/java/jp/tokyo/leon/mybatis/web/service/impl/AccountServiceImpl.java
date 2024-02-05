package jp.tokyo.leon.mybatis.web.service.impl;

import jp.tokyo.leon.mybatis.web.dao.AccountDao;
import jp.tokyo.leon.mybatis.web.exceptions.MoneyNotEnoughException;
import jp.tokyo.leon.mybatis.web.exceptions.TransferException;
import jp.tokyo.leon.mybatis.web.pojo.Account;
import jp.tokyo.leon.mybatis.web.service.AccountService;
import jp.tokyo.leon.mybatis.web.utils.GenerateDaoProxy;
import jp.tokyo.leon.mybatis.web.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * @author leon
 * @date 2024/2/2 01:21
 */
public class AccountServiceImpl implements AccountService {

    // private final AccountDao accountDao = (AccountDao) GenerateDaoProxy.generate(SqlSessionUtil.openSession(), AccountDao.class);

    // 在mybatis中也提供了相关机制，动态为我们生成dao接口的实现类
    // mybatis当中实际上采用了代理模式，在内存只中生成dao接口的代理类，然后创建实例
    // 使用mybatis的这种代理机制的前提：SqlMapper.xml的namespace必须是dao接口的全限定类名，id必须是dao接口的方法名

    private final AccountDao accountDao = SqlSessionUtil.openSession().getMapper(AccountDao.class);
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
        count += accountDao.updateByActno(toAct);
        if (count != 2) {
            throw new TransferException("转账异常，原因未知");
        }
        session.commit();
        SqlSessionUtil.close(session);
    }
}
