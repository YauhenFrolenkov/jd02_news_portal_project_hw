<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<title>Просмотр новости</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light d-flex flex-column min-vh-100">

<!-- HEADER -->
<header class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="/Controller/index">NewsPortal</a>
        <div class="ms-auto d-flex align-items-center">
            <span class="text-light me-3">
                Привет, <c:choose>
                    <c:when test="${not empty sessionScope.auth}">
                        ${sessionScope.auth.name}
                    </c:when>
                    <c:otherwise>
                        Гость
                    </c:otherwise>
                </c:choose>
            </span>
            <a href="NewsPortalController?command=page_news_list" class="btn btn-outline-light me-2">Все новости</a>
            <c:if test="${not empty sessionScope.auth}">
                <a href="NewsPortalController?command=page_add_news" class="btn btn-outline-light me-2">Добавить новость</a>
                <a href="NewsPortalController?command=page_user_home" class="btn btn-outline-light me-2">Мой кабинет</a>
            </c:if>
            <a href="NewsPortalController?command=page_auth" class="btn btn-outline-light">Выйти</a>
        </div>
    </div>
</header>

<!-- MAIN -->
<main class="flex-fill container py-5">

    <div class="card shadow-lg p-4">
        <h2 class="card-title mb-3">${news.title}</h2>
        <p class="text-muted mb-2">Опубликовано: ${news.publishDate}</p>
        <hr>
        <p class="card-text">${news.brief}</p>
        <p class="card-text">${news.content}</p> <!-- Здесь предполагается путь к контенту или сам контент -->
        
        <c:if test="${not empty sessionScope.auth}">
            <div class="mt-3">
                <a href="NewsPortalController?command=page_edit_news&id=${news.id}" class="btn btn-warning btn-sm me-2">Редактировать</a>
                <a href="NewsPortalController?command=do_delete_news&id=${news.id}" class="btn btn-danger btn-sm"
                   onclick="return confirm('Вы действительно хотите удалить эту новость?');">Удалить</a>
            </div>
        </c:if>
    </div>

</main>

<!-- FOOTER -->
<footer class="bg-dark text-light text-center py-3 mt-auto">
    &copy; 2025 NewsPortal. Все права защищены.
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
