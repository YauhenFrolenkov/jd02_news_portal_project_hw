<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<title>Личный кабинет</title>
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
    rel="stylesheet">
</head>
<body class="bg-light d-flex flex-column min-vh-100">

    <!-- HEADER -->
    <header class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="NewsPortalController?command=page_main">NewsPortal</a>
            <div class="ms-auto d-flex align-items-center">
                <span class="text-light me-3"> Привет,
                    <c:choose>
                        <c:when test="${not empty sessionScope.auth}">
                            ${sessionScope.auth.name}
                        </c:when>
                        <c:otherwise>
                            Гость
                        </c:otherwise>
                    </c:choose>
                </span>

                <a href="NewsPortalController?command=page_main" class="btn btn-outline-light me-2">Главная</a>
                <a href="NewsPortalController?command=page_news_list" class="btn btn-outline-light me-2">Все новости</a>
                <c:if test="${not empty sessionScope.auth}">
                    <a href="NewsPortalController?command=page_user_home" class="btn btn-outline-light me-2">Мой кабинет</a>
                    <a href="NewsPortalController?command=page_add_news" class="btn btn-success me-2">Добавить новость</a>
                    <a href="NewsPortalController?command=do_logout" class="btn btn-outline-light">Выйти</a>
                </c:if>

                <c:if test="${empty sessionScope.auth}">
                    <a href="NewsPortalController?command=do_auth" class="btn btn-outline-light">Войти</a>
                </c:if>
            </div>
        </div>
    </header>

    <!-- MAIN -->
    <main class="flex-fill container py-5">
        <!-- Приветствие -->
        <div class="card shadow-lg p-4 mb-4 text-center">
            <h1 class="h3 mb-3">Добро пожаловать на NewsPortal!</h1>
            <p class="mb-0">Вы успешно вошли в систему. Ниже представлены последние новости.</p>
        </div>

        <!-- Список новостей -->
        <div class="row g-4">
            <c:choose>
                <c:when test="${not empty topNews}">
                    <c:forEach var="news" items="${topNews}">
                        <div class="col-md-6 col-lg-4">
                            <div class="card h-100 shadow-sm">
                                <img src="https://picsum.photos/400/200?random=${news.id}" class="card-img-top" alt="${news.title}">
                                <div class="card-body d-flex flex-column">
                                    <h5 class="card-title">${news.title}</h5>
                                    <p class="card-text text-muted flex-grow-1">${news.brief}</p>
                                    <a href="NewsPortalController?command=page_view_news&id=${news.id}" class="btn btn-primary mt-auto">Подробнее</a>
                                </div>
                                <div class="card-footer text-muted small">Опубликовано: ${news.publishDate}</div>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="col-12">
                        <div class="alert alert-info">Пока нет новостей.</div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </main>

    <!-- FOOTER -->
    <footer class="bg-dark text-light text-center py-3 mt-auto">
        &copy; 2025 NewsPortal. Все права защищены.
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
