package tripplan.controller;

import tripplan.service.PlanService;
import tripplan.service.PlanServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/plan")
public class PlanController extends HttpServlet {

    private PlanService planService;

    @Override
    public void init() throws ServletException {
        planService = PlanServiceImpl.getPlanService();
    }
}
