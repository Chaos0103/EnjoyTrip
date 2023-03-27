package member.controller;

import member.dto.LoginMember;
import member.service.AccountService;
import member.service.AccountServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/account")
public class AccountController extends HttpServlet {

    private AccountService accountService;

    @Override
    public void init() throws ServletException {
        accountService = AccountServiceImpl.getAccountService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String path ="";
        switch (action){
            case "mvlogin":
                response.sendRedirect(request.getContextPath()+"/account/login.jsp");
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
        System.out.println("AccountController.doLogin");
        System.out.println("userId = " + userId);
        System.out.println("userPwd = " + userPwd);
        try{
            LoginMember loginMember = accountService.login(userId, userPwd);
            if(loginMember != null) {
//				session 설정
                HttpSession session = request.getSession();
                session.setAttribute("userinfo", loginMember);
                System.out.println("loginMember = " + loginMember);

//				cookie 설정
//                String idsave = request.getParameter("saveid");
//                if("ok".equals(idsave)) { //아이디 저장을 체크 했다면.
//                    Cookie cookie = new Cookie("ssafy_id", userId);
//                    cookie.setPath(request.getContextPath());
////					크롬의 경우 400일이 최대
////					https://developer.chrome.com/blog/cookie-max-age-expires/
//                    cookie.setMaxAge(60 * 60 * 24 * 365 * 40); //40년간 저장.
//                    response.addCookie(cookie);
//                } else { //아이디 저장을 해제 했다면.
//                    Cookie cookies[] = request.getCookies();
//                    if(cookies != null) {
//                        for(Cookie cookie : cookies) {
//                            if("ssafy_id".equals(cookie.getName())) {
//                                cookie.setMaxAge(0);
//                                response.addCookie(cookie);
//                                break;
//                            }
//                        }
//                    }
//                }
                return "/index.jsp";
            } else {
                request.setAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요.");
                return "/account/login.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "로그인 중 에러 발생!!!");
            return "/error/error.jsp";
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
