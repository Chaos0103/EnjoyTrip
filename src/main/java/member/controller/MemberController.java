package member.controller;

import common.exception.SignUpException;
import common.validation.SignUpValidation;
import common.validation.dto.InvalidResponse;
import common.validation.dto.MemberRequest;
import member.Member;
import member.dto.LoginMember;
import member.dto.MemberAddDto;
import member.dto.MemberDto;
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
            case "view"://마이페이지조회
                path = viewMypage(request, response);
                forward(request, response, path);
                break;
            case "myArticle"://내가 쓴 게시물 보기
//                path = viewMypage(request, response);
//                redirect(request, response, path);
                break;
            case "myHotplace"://내가 등록한 핫플레이스 목록 보비
//                path = viewMypage(request, response);
//                redirect(request, response, path);
                break;
            case "modifyPw"://pw수정
                path = modifyPw(request, response);
                RequestDispatcher dispatcher = request.getRequestDispatcher(path);
                dispatcher.forward(request, response);
//                redirect(request, response, path);
                break;
            case "mvModifyPw"://pw수정폼 보기
                path = mvModifyPw(request, response);
                forward(request, response, path);
                break;

            case "modifyNickname":
                path = modifyNickname(request, response);
                redirect(request, response, path);
                break;
            case "mvModifyNickname":
                path = mvModifyNickname(request, response);
                forward(request, response, path);
                break;

//            case "modifyEmail":
//                path = modifyEmail(request, response);
//                redirect(request, response, path);
//                break;
//            case "mvModifyEmail":
//                path = mvModifyEmail(request, response);
//                forward(request, response, path);
//                break;
//
//            case "modifyTel":
//                path = modifyTel(request, response);
//                redirect(request, response, path);
//                break;
//            case "mvModifyTel":
//                path = mvModifyTel(request, response);
//                forward(request, response, path);
//                break;

            case "withdrawal"://탈퇴
                doWithdrawal(request, response);
                break;
        }
    }

    private String mvModifyNickname(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("currShow","modifyNickname");
        System.out.println("session.getAttribute(\"currShow\") = " + session.getAttribute("currShow"));
        return "/member/mypage.jsp";
    }

    private String modifyNickname(HttpServletRequest request, HttpServletResponse response) {
        //해야함

        return "";
    }

    private String mvModifyPw(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("currShow","modifyPw");
        System.out.println("session.getAttribute(\"currShow\") = " + session.getAttribute("currShow"));
        return "/member/mypage.jsp";
    }

    //      비밀번호 수정 아래 해야함
    private String modifyPw(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");

        String currPw = request.getParameter("currPw");
        String newPw = request.getParameter("newPw");
        String newPwCheck = request.getParameter("newPwCheck");



        if(!currPw.equals(loginMember.getLoginPw())){
            request.setAttribute("msg","비밀번호가 틀렸습니다.");
            //dis
            //for
            return "/member/mypage.jsp";
        }
        if(!newPw.equals(newPwCheck)){
            request.setAttribute("msg","비밀번호가 일치하지 않습니다.");
            return "/member/mypage.jsp";
        }
        if(currPw.equals(newPw)){
            request.setAttribute("msg","기존 비밀번호와 같습니다.");
            return "/member/mypage.jsp";
        }


        memberService.changePassword(loginMember.getId(),newPw);
        request.setAttribute("msg","비밀번호 변경이 완료되었습니다. 다시 로그인 하세요.");
        return "/account?action=logout";
    }

    private String viewMypage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");

        MemberDto dto = memberService.myPage(loginMember.getId()).get();
        String birth1 = dto.getBirth().substring(0,2);
        String birth2 = dto.getBirth().substring(2,4);
        String birth3 = dto.getBirth().substring(4,6);
        if(Integer.parseInt(dto.getGender())>2){
            dto.setBirth("20"+birth1+"년 "+birth2+"월 "+birth3+"일");
        }else {
            dto.setBirth("19"+birth1+"년 "+birth2+"월 "+birth3+"일");
        }

        if(Integer.parseInt(dto.getGender())%2==0) {
            dto.setGender("여성");
        }else{
            dto.setGender("남성");
        }

        session.setAttribute("currShow","myPage");
        session.setAttribute("loginUserDto", dto);
        return "/member/mypage.jsp";
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
