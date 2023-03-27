<%--
  Created by IntelliJ IDEA.
  User: leeyr
  Date: 2023/03/27
  Time: 9:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
    <div class="position-sticky pt-3 sidebar-sticky">
        <c:set var="currShow" value="myPage"/>
        <ul class="nav flex-column">
            <li class="nav-item">
                <c:if test="${currShow eq 'myPage'}">
                <a class="nav-link active" aria-current="page" href="">
                    </c:if>
                    <c:if test="${currShow != 'myPage'}">
                    <a class="nav-link " aria-current="page" href="${root}/member?action=view">
                        </c:if>
                        <span data-feather="home" class="align-text-bottom"></span>
                        <i class="bi bi-person"></i>
                        마이페이지
                    </a>
            </li>
            <li class="nav-item ">
                <c:if test="${currShow eq 'myArticle'}">
                <a class="nav-link active" aria-current="page" href="#">
                    </c:if>
                    <c:if test="${currShow != 'myArticle'}">
                    <a class="nav-link " aria-current="page" href="#">
                        </c:if>
                        <span data-feather="file" class="align-text-bottom"></span>
                        내가 쓴 게시물
                    </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">
                    <span data-feather="shopping-cart" class="align-text-bottom"></span>
                    내가 등록한 핫플레이스
                </a>
            </li>
            <li class="nav-item mt-5">
                <c:if test="${currShow eq 'modifyPw'}">
                <a class="nav-link active" aria-current="page" href="">
                    </c:if>
                    <c:if test="${currShow != 'modifyPw'}">
                    <a class="nav-link " aria-current="page" href="${root}/member?action=view">
                        </c:if>
                        <span data-feather="home" class="align-text-bottom"></span>
                        <i class="bi bi-person"></i>
                        비밀번호 변경
                    </a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="#">
                    <span data-feather="home" class="align-text-bottom"></span>
                    <i class="bi bi-person"></i>
                    별명 변경
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="#">
                    <span data-feather="home" class="align-text-bottom"></span>
                    <i class="bi bi-person"></i>
                    이메일 변경
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="#">
                    <span data-feather="home" class="align-text-bottom"></span>
                    <i class="bi bi-person"></i>
                    전화번호 변경
                </a>
            </li>

            <li class="nav-item mt-5">
                <a class="nav-link" href="#">
                    <span data-feather="shopping-cart" class="align-text-bottom"></span>
                    회원탈퇴
                </a>
            </li>
        </ul>
    </div>
</nav>