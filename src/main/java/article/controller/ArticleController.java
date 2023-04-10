package article.controller;

import article.dto.ArticleDetailDto;
import article.dto.ArticleDto;
import article.dto.ArticleSearch;
import article.service.ArticleService;
import article.service.ArticleServiceImpl;
import common.Page;
import common.exception.ArticleException;
import common.validation.ArticleValidation;
import common.validation.dto.ArticleRequest;
import common.validation.dto.InvalidResponse;
import member.dto.LoginMember;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static common.exception.ExceptionMessage.ARTICLE_EXCEPTION;

@WebServlet("/article")
public class ArticleController extends HttpServlet {

    private ArticleService articleService;

    @Override
    public void init() throws ServletException {
        articleService = ArticleServiceImpl.getArticleService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "mvwrite":
                doMvWriter(request, response);
                break;
            case "write":
                doWriter(request, response);
                break;
            case "list":
                doList(request, response);
                break;
            case "detail":
                doDetail(request, response);
                break;
            case "mvedit":
                doMvedit(request, response);
                break;
            case "edit":
                doEdit(request, response);
                break;
            case "remove":
                doRemove(request, response);
                break;
            default:
                forward(request, response, "/error/ready.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doGet(request, response);
    }

    private void doMvWriter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
        if (loginMember == null) {
            request.setAttribute("msg", "로그인 후 사용해주세요.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/article/articleList.jsp");
            dispatcher.forward(request, response);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/article/addArticle.jsp");
        dispatcher.forward(request, response);
    }

    private void doWriter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }

        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");

        String title = request.getParameter("title");
        String content = request.getParameter("content");

        ArticleValidation articleValidation = new ArticleValidation();
        ArticleRequest articleRequest = ArticleRequest.builder()
                .title(title)
                .content(content)
                .build();

        List<InvalidResponse> validate = articleValidation.validate(articleRequest);
        if (!validate.isEmpty()) {
            throw new ArticleException(ARTICLE_EXCEPTION);
        }

        ArticleDto articleDto = ArticleDto.builder()
                .title(title)
                .content(content)
                .build();

        int result = articleService.addArticle(loginMember.getId(), articleDto);

        response.sendRedirect(request.getContextPath() + "/article?action=list");
    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String writer = request.getParameter("writer");
        String hit = request.getParameter("hit");
        if (hit == null) {
            hit = "desc";
        }
        String createdDate = request.getParameter("createdDate");
        if (createdDate == null) {
            createdDate = "desc";
        }

        int pageNum = 1;
        int amount = 10;

        if (request.getParameter("pageNum") != null && request.getParameter("amount") != null) {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
            amount = Integer.parseInt(request.getParameter("amount"));
        }


        ArticleSearch condition = ArticleSearch.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .hit(hit)
                .createdDate(createdDate)
                .build();

        List<ArticleDto> articles = articleService.searchArticles(condition);
        int totalCount = articleService.getTotalCount();
        Page page = new Page(pageNum, amount, totalCount);

        request.setAttribute("page", page);
        request.setAttribute("articles", articles);
        forward(request, response, "/article/articleList.jsp");
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long articleId = Long.parseLong(request.getParameter("articleId"));

        ArticleDetailDto article = articleService.searchArticle(articleId);

        request.setAttribute("article", article);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/article/viewArticle.jsp");
        dispatcher.forward(request, response);
    }

    private void doMvedit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long articleId = Long.parseLong(request.getParameter("articleId"));

        ArticleDetailDto article = articleService.searchArticle(articleId);

        request.setAttribute("article", article);
        forward(request, response, "/article/editArticle.jsp");
    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LoginMember loginMember = (LoginMember) session.getAttribute("userinfo");
        if (loginMember == null) {
            return;
        }

        Long articleId = Long.parseLong(request.getParameter("articleId"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        ArticleDto articleDto = ArticleDto.builder()
                .title(title)
                .content(content)
                .build();

        int result = articleService.editArticle(articleId, loginMember.getId(), articleDto);
        redirect(request, response, "/article?action=detail&articleId=" + articleId);
    }

    private void doRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + path);
    }
}
