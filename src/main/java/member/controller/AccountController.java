package member.controller;

import member.dto.LoginMember;
import member.service.AccountService;
import member.service.AccountServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/account")
public class AccountController extends HttpServlet {

    private AccountService accountService;

    @Override
    public void init() {
        accountService = AccountServiceImpl.getAccountService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String path ="";
        switch (action){
            case "mvlogin":
                forward(request, response, "/account/login.jsp");
                break;
            case "login":
                path = doLogin(request,response);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
                requestDispatcher.forward(request,response);
                break;
            case "logout":
                path = logout(request, response);
                redirect(request, response, path);
                break;
            default:
                break;
        }
    }

    private String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "/index.jsp";
    }

    private String doLogin(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String userPwd = request.getParameter("userPassword");
        try{
            LoginMember loginMember = accountService.login(userId, userPwd);
            if(loginMember != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userinfo", loginMember);
                return "/index.jsp";
            } else {
                request.setAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요.");
                return "/account/login.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요.");
            return "/account/login.jsp";
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doGet(request, response);
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        response.sendRedirect(request.getContextPath() + path);
    }
}
