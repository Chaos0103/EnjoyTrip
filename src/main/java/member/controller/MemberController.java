package member.controller;

import member.Member;
import member.dto.MemberAddDto;
import member.service.MemberService;
import member.service.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/member")
public class MemberController extends HttpServlet {

    private MemberService memberService;

    @Override
    public void init() {
        memberService = MemberServiceImpl.getMemberService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "register":
                doRegister(request, response);
                break;
            case "view":
                break;
            case "modify":
                break;
            case "withdrawal":
                doWithdrawal(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doGet(request, response);
    }

    private void doRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginId = request.getParameter("loginId");
        String loginPw = request.getParameter("loginPw");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String nickname = request.getParameter("nickname");
        String birth = request.getParameter("birth");
        String gender = request.getParameter("gender");

        MemberAddDto memberAddDto = new MemberAddDto(loginId, loginPw, username, email, phone, nickname, birth, gender);
        memberService.signUp(memberAddDto);

        // TODO: 2023/03/25 reload url
//        response.sendRedirect();
    }

    private void doModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginPw = request.getParameter("loginPw");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String nickname = request.getParameter("nickname");
    }

    private void doWithdrawal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember == null) {
            response.sendRedirect("/");
            return;
        }

        String loginPw = request.getParameter("loginPw");

        memberService.withdrawal(loginMember.getMemberId(), loginPw);

        response.sendRedirect("/");
    }
}
