package tripplan.controller;

import attraction.AttractionInfo;
import attraction.service.AttractionService;
import attraction.service.AttractionServiceImpl;
import common.Page;
import member.dto.LoginMember;
import tripplan.dto.DetailPlanDto;
import tripplan.dto.PlanListDto;
import tripplan.dto.PlanSearch;
import tripplan.dto.TripPlanDto;
import tripplan.service.PlanService;
import tripplan.service.PlanServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/tripPlan")
public class PlanController extends HttpServlet {

    private PlanService planService;
    private AttractionService attractionService;

    @Override
    public void init() throws ServletException {
        planService = PlanServiceImpl.getPlanService();
        attractionService = AttractionServiceImpl.getAttractionService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "mvcreate":
                doMvCreate(request, response);
                break;
            case "create":
                doCreate(request, response);
                break;
            case "list":
                doList(request, response);
                break;
            case "detail":
                doDetail(request, response);
                break;
//            case "mvadd":
//                break;
            case "add":
                break;
            case "deletePlan":
                break;
            case "remove":
                break;
        }
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long tripPlanId = Long.parseLong(request.getParameter("tripPlanId"));
        TripPlanDto tripPlan = planService.showPlan(tripPlanId);
        request.setAttribute("tripPlan", tripPlan);
        //<c:forEach items=${tripPlan.detailPlans} var="detailPlan">
        forward(request, response, "/tripplan/viewPlan.jsp");
    }

    private void doCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
        String title = request.getParameter("planTitle");
        String[] contentList = request.getParameter("contentList").split(",");
        List<Integer> contentIdList = new ArrayList<>();
        for (String contentId : contentList) {
            contentIdList.add(Integer.parseInt(contentId));
        }

        planService.addTripPlan(loginMember.getId(), title);
        Long tripPlanId = planService.getTripPlanId(loginMember.getId());

        List<AttractionInfo> attractionInfos = attractionService.searchAttraction(contentIdList);
        for (AttractionInfo attractionInfo : attractionInfos) {
            System.out.println("attractionInfo.getId() = " + attractionInfo.getId());
            planService.addDetailPlan(loginMember.getId(), tripPlanId, attractionInfo.getId());
        }

//        for (String contentId : contentList) {
////            planService.addDetailPlan(loginMember.getId(), tripPlanId, Integer.parseInt(contentId));
//        }

        redirect(request, response, "/tripPlan?action=detail&tripPlanId=" + tripPlanId);
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

    private void doMvCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
        if (loginMember == null) {
            request.setAttribute("msg", "로그인 후 이용해주세요.");
            forward(request, response, "/account/login.jsp");
            return;
        }
        forward(request, response, "/tripplan/createPlan.jsp");
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + path);
    }
}
