package tripplan.controller;

import common.Page;
import tripplan.dto.PlanListDto;
import tripplan.dto.PlanSearch;
import tripplan.service.PlanService;
import tripplan.service.PlanServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tripPlan")
public class PlanController extends HttpServlet {

    private PlanService planService;

    @Override
    public void init() throws ServletException {
        planService = PlanServiceImpl.getPlanService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "mvcreate":
                doMvCreate(request, response);
                break;
            case "create":
                break;
            case "list":
                doList(request, response);
                break;
            case "detail":
                break;
            case "mvadd":
                break;
            case "add":
                break;
            case "deletePlan":
                break;
            case "remove":
                break;
        }
    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String condition = request.getParameter("condition") == null ? "" : request.getParameter("condition");

        int pageNum = 1;
        int amount = 10;

        if (request.getParameter("pageNum") != null && request.getParameter("amount") != null) {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
            amount = Integer.parseInt(request.getParameter("amount"));
        }

        PlanSearch planSearch = PlanSearch.builder()
                .condition(condition)
                .build();

        List<PlanListDto> plans = planService.searchPlans(planSearch, pageNum, amount);
        int totalCount = planService.getTotalCount();
        Page page = new Page(pageNum, amount, totalCount);

        request.setAttribute("page", page);
        request.setAttribute("plans", plans);
        forward(request, response, "/tripplan/tripList.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doGet(request, response);
    }

    private void doMvCreate(HttpServletRequest request, HttpServletResponse response) {
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + path);
    }
}
