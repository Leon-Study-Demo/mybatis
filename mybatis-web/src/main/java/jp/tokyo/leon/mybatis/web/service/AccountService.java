package jp.tokyo.leon.mybatis.web.service;

import jp.tokyo.leon.mybatis.web.exceptions.MoneyNotEnoughException;
import jp.tokyo.leon.mybatis.web.exceptions.TransferException;

/**
 * @author leon
 * @date 2024/2/2 01:19
 */
public interface AccountService {
    void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, TransferException;
}
