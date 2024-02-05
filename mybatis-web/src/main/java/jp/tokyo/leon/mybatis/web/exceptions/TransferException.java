package jp.tokyo.leon.mybatis.web.exceptions;

/**
 * @author leon
 * @date 2024/2/5 00:57
 */
public class TransferException extends Exception{
    public TransferException() {
    }

    public TransferException(String message) {
        super(message);
    }
}
