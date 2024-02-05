package jp.tokyo.leon.mybatis.web.controller;

import jp.tokyo.leon.mybatis.web.exceptions.MoneyNotEnoughException;
import jp.tokyo.leon.mybatis.web.exceptions.TransferException;
import jp.tokyo.leon.mybatis.web.service.AccountService;
import jp.tokyo.leon.mybatis.web.service.impl.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author leon
 * @date 2024/2/2 01:15
 */
 @WebServlet("/transfer")
public class AccountServlet extends HttpServlet {

    private final AccountService accountService = new AccountServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 获取表单数据
        String fromActno = req.getParameter("fromActno");
        String toActno = req.getParameter("toActno");
        double money = Double.parseDouble(req.getParameter("money"));

        // 调用service的转账方法完成转账

        try {
            accountService.transfer(fromActno, toActno, money);
            resp.sendRedirect(req.getContextPath() + "/success.html");
        } catch (MoneyNotEnoughException e) {
            resp.sendRedirect(req.getContextPath() + "/error1/html");
        } catch (TransferException e) {
            resp.sendRedirect(req.getContextPath() + "/error2.html");
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/error2.html");
        }

        // 调用view完成展示结果



    }
}
