package article.controller;

import article.service.ArticleService;
import article.service.ArticleServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/article")
public class ArticleController extends HttpServlet {

    private ArticleService articleService;

    @Override
    public void init() throws ServletException {
        articleService = ArticleServiceImpl.getArticleService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/error/ready.jsp");
        dispatcher.forward(request, response);
    }
}
