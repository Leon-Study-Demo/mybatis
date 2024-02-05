package jp.tokyo.leon.mybatis.web.exceptions;

/**
 * @author leon
 * @date 2024/2/5 00:53
 */
public class MoneyNotEnoughException extends Exception{
    public MoneyNotEnoughException(){}

    public MoneyNotEnoughException(String msg) {
        super(msg);
    }
}
