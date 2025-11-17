<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NewsPortal — Добавить новость</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;800&display=swap" rel="stylesheet">

<style>
:root {
    --accent: #FFD60A;
    --accent-hover: #e6c200;
    --dark: #000;
    --light: #fff;
}

/* ====== Базовые стили ====== */
body {
    font-family: 'Inter', sans-serif;
    background: linear-gradient(135deg, #fffef8, #fff4cc);
    color: #222;
    margin: 0;
    padding: 0;
    min-height: 100vh;
}

/* ====== Навбар ====== */
.navbar {
    background-color: var(--dark);
    letter-spacing: 0.5px;
}

.navbar-brand {
    font-weight: 800;
    font-size: 1.4rem;
    text-transform: uppercase;
    color: var(--accent) !important;
}

.navbar .btn-outline-light:hover {
    background-color: var(--accent);
    color: var(--dark);
    border-color: var(--accent);
}

/* ====== Карточка формы ====== */
.card {
    border: none;
    border-radius: 16px;
    overflow: hidden;
    box-shadow: 0 4px 12px rgba(0,0,0,0.05);
    background-color: #fff;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    padding: 30px;
}

.card:hover {
    transform: translateY(-5px);
    box-shadow: 0 8px 20px rgba(0,0,0,0.1);
}

/* ====== Заголовок формы ====== */
.form-title {
    font-weight: 800;
    font-size: 1.8rem;
    text-transform: uppercase;
    color: var(--dark);
    margin-bottom: 25px;
    text-align: center;
}

/* ====== Метки и поля ====== */
.form-label {
    font-weight: 600;
}

.form-control, .form-select {
    border-radius: 8px;
    padding: 10px;
    font-size: 0.95rem;
}

/* ====== Кнопки ====== */
.btn-primary {
    background-color: var(--accent);
    color: var(--dark);
    border: none;
    text-transform: uppercase;
    letter-spacing: 0.5px;
    font-weight: 600;
}

.btn-primary:hover {
    background-color: var(--accent-hover);
    color: var(--dark);
}

.btn-secondary {
    background-color: #888;
    color: #fff;
    border: none;
}

.btn-secondary:hover {
    background-color: #666;
}

/* ====== Сообщения об ошибке ====== */
.alert {
    border-radius: 12px;
    font-weight: 500;
    margin-bottom: 20px;
}

/* ====== Footer ====== */
footer {
    background-color: var(--dark);
    color: var(--light);
}
footer a {
    color: var(--accent);
    text-decoration: none;
    margin: 0 8px;
    font-size: 0.9rem;
}
footer a:hover {
    text-decoration: underline;
}
</style>
</head>

<body class="d-flex flex-column min-vh-100">

<!-- HEADER -->
<header class="navbar navbar-expand-lg navbar-dark py-3">
    <div class="container">
        <a class="navbar-brand" href="NewsPortalController?command=page_main">NewsPortal</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0 align-items-lg-center">
                <span class="text-light me-3">Привет, 
                    <c:choose>
                        <c:when test="${not empty sessionScope.auth}">
                            ${sessionScope.auth.name}
                        </c:when>
                        <c:otherwise>Гость</c:otherwise>
                    </c:choose>
                </span>
                <li class="nav-item me-2"><a class="btn btn-outline-light" href="NewsPortalController?command=page_news_list">Новости</a></li>
                <c:if test="${not empty sessionScope.auth}">
                    <li class="nav-item me-2"><a class="btn btn-outline-light" href="NewsPortalController?command=page_user_home">Кабинет</a></li>
                </c:if>
                <li class="nav-item me-2"><a class="btn btn-outline-light" href="NewsPortalController?command=do_logout">Выйти</a></li>
            </ul>
        </div>
    </div>
</header>

<!-- MAIN -->
<main class="flex-fill container my-5">
    <div class="card mx-auto shadow-lg" style="max-width: 700px;">
        <h1 class="form-title">Добавить новость</h1>

        <!-- Ошибка -->
        <c:if test="${param.error == 'true'}">
            <div class="alert alert-danger text-center">Произошла ошибка. Проверьте корректность введённых данных.</div>
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

            <div class="text-center mt-4 d-flex justify-content-center gap-3 flex-wrap">
                <button type="submit" class="btn btn-primary">Сохранить</button>
                <a href="NewsPortalController?command=page_news_list" class="btn btn-secondary">Отмена</a>
            </div>
        </form>
    </div>
</main>

<!-- FOOTER -->
<footer class="py-4 mt-auto text-center">
    <div class="container">
        <p class="mb-1">&copy; 2025 NewsPortal. Все права защищены.</p>
        <a href="/Controller/about">О нас</a> | 
        <a href="/Controller/contact">Контакты</a>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
