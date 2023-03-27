package notion.controller;

import common.Page;
import member.Member;
import notion.dto.NotionDto;
import notion.service.NotionService;
import notion.service.NotionServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/notion")
public class NotionController extends HttpServlet {

    private NotionService notionService;

    @Override
    public void init() {
        notionService = NotionServiceImpl.getNotionService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "list":
                doList(request, response);
                break;
            case "mvwrite":
                doMvWriter(request, response);
                break;
            case "write":
                doWriter(request, response);
                break;
            case "mvmodify":
                break;
            case "modify":
                break;
            case "remove":
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doGet(request, response);
    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNum = 1;
        int amount = 10;

        if (request.getParameter("pageNum") != null && request.getParameter("amount") != null) {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
            amount = Integer.parseInt(request.getParameter("amount"));
        }

        List<NotionDto> notions = notionService.searchNotions(pageNum, amount);
        int totalCount = notionService.getTotalCount();
        Page page = new Page(pageNum, amount, totalCount);
        System.out.println("page = " + page);
        request.setAttribute("page", page);
        request.setAttribute("notions", notions);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/notion/notionList.jsp");
        dispatcher.forward(request, response);
    }

    private void doMvWriter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/notion/addNotion.jsp");
        dispatcher.forward(request, response);
    }

    private void doWriter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }

        Member loginMember = (Member) session.getAttribute("userinfo");

        String title = request.getParameter("title");
        String content = request.getParameter("content");

        NotionDto notionDto = NotionDto.builder()
                .title(title)
                .content(content)
                .build();

        int result = notionService.addNotion(loginMember.getId(), notionDto);
        if (result == 0) {
            return;
        }
        response.sendRedirect(request.getContextPath() + "/notion&action=list");
    }
}
