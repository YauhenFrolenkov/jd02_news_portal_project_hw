<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<title>Добавить новость</title>
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
            <a href="NewsPortalController?command=page_news_list" class="btn btn-outline-light me-2">Новости</a>
            <c:if test="${not empty sessionScope.auth}">
                <a href="NewsPortalController?command=page_user_home" class="btn btn-outline-light me-2">Мой кабинет</a>
            </c:if>
            <a href="NewsPortalController?command=page_auth" class="btn btn-outline-light">Выйти</a>
        </div>
    </div>
</header>

<!-- MAIN -->
<main class="flex-fill container py-5">
    <div class="card shadow-lg p-4 mb-5">
        <h1 class="h3 mb-4 text-center">Добавить новость</h1>
        
        <!-- Ошибка, если была -->
        <c:if test="${param.error == 'true'}">
            <div class="alert alert-danger" role="alert">
                Произошла ошибка. Проверьте корректность введённых данных.
            </div>
        </c:if>

        <form action="NewsPortalController" method="post">
            <input type="hidden" name="command" value="do_add_news">
            
            <div class="mb-3">
                <label for="title" class="form-label">Заголовок</label>
                <input type="text" class="form-control" id="title" name="title" required>
            </div>

            <div class="mb-3">
                <label for="brief" class="form-label">Краткое описание</label>
                <textarea class="form-control" id="brief" name="brief" rows="3" required></textarea>
            </div>

            <div class="mb-3">
                <label for="contentPath" class="form-label">Ссылка на полный текст</label>
                <input type="text" class="form-control" id="contentPath" name="contentPath" required>
            </div>

            <div class="mb-3">
                <label for="status" class="form-label">Статус</label>
                <select class="form-select" id="status" name="action">
                    <option value="draft" <c:if test="${defaultStatus == 2}">selected</c:if>>Черновик</option>
                    <option value="publish">Опубликовать</option>
                </select>
            </div>

            <div class="mb-3">
                <label class="form-label">Дата публикации</label>
                <input type="text" class="form-control" value="${currentDate}" readonly>
            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-primary">Сохранить</button>
                <a href="NewsPortalController?command=page_news_list" class="btn btn-secondary">Отмена</a>
            </div>
        </form>
    </div>
</main>

<!-- FOOTER -->
<footer class="bg-dark text-light text-center py-3 mt-auto">
    &copy; 2025 NewsPortal. Все права защищены.
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
