package member.controller;

import common.exception.SignUpException;
import common.validation.SignUpValidation;
import common.validation.dto.InvalidResponse;
import common.validation.dto.MemberRequest;
import member.Member;
import member.dto.MemberAddDto;
import member.service.MemberService;
import member.service.MemberServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

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
        String path = "";
        switch (action) {
            case "register"://가입
                doRegister(request, response);
                break;
            case "mvregister"://가입
                forward(request, response, "/member/addMember.jsp");
                break;
            case "mvview"://마이페이지로 이동
                path = "/member/mypage.jsp";
                redirect(request, response, path);
                break;
            case "view"://마이페이지조회
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
        String gender = request.getParameter("memberGender").substring(0, 1);

        SignUpValidation validation = new SignUpValidation();
        MemberRequest memberRequest = MemberRequest.builder()
                .loginId(loginId)
                .loginPw(loginPw)
                .username(username)
                .email(email)
                .phone(phone)
                .nickname(nickname)
                .birth(birth)
                .gender(gender)
                .build();
        List<InvalidResponse> responses = validation.validate(memberRequest);

        if (!responses.isEmpty()) {
            throw new SignUpException();
        }

        MemberAddDto memberAddDto = MemberAddDto.builder()
                .loginId(loginId)
                .loginPw(loginPw)
                .username(username)
                .email(email)
                .phone(phone)
                .nickname(nickname)
                .birth(birth)
                .gender(gender)
                .build();
        memberService.signUp(memberAddDto);

        response.sendRedirect(request.getContextPath() + "/account/login.jsp");
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

        memberService.withdrawal(loginMember.getId(), loginPw);

        response.sendRedirect("/");
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
