package member.controller;

import member.Authority;
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
            case "register"://가입
                doRegister(request, response);
                break;
            case "mvregister"://가입
                System.out.println("MemberController.doGet");
                response.sendRedirect(request.getContextPath()+"/member/addMember.jsp");
                break;
            case "view"://정보조회
                break;
            case "modify"://정보수정
                break;
            case "withdrawal"://탈퇴
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
        String loginId = request.getParameter("memberId");
        String loginPw = request.getParameter("memberPassword");
        String username = request.getParameter("memberName");
        String email = request.getParameter("memberEmail");
        String phone = request.getParameter("memberPhone");
        String nickname = request.getParameter("memberNickname");
        String birth = request.getParameter("memberBirth");
        String gender = request.getParameter("memberGender").substring(0,1);

        System.out.println("MemberController.doRegister");
        System.out.println("loginId = " + loginId);


        MemberAddDto memberAddDto = new MemberAddDto(loginId, loginPw, username, email, phone, birth, gender, nickname, Authority.CLIENT);
        memberService.signUp(memberAddDto);

        // TODO: 2023/03/25 reload url
        response.sendRedirect(request.getContextPath()+"/account/login.jsp");
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
