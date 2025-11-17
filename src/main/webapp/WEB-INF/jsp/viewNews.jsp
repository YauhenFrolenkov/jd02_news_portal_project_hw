<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NewsPortal — Просмотр новости</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;800&display=swap"
	rel="stylesheet">

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

/* ====== Заголовок новости ====== */
.news-title {
	font-weight: 800;
	font-size: 2rem;
	text-transform: uppercase;
	color: var(--dark);
	margin: 30px 0 20px 0;
	text-align: left; /* выравнивание по левому краю */
}

/* ====== Карточка новости ====== */
.news-card {
	border: none;
	border-radius: 16px;
	overflow: hidden;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
	transition: transform 0.3s ease, box-shadow 0.3s ease;
	background-color: #fff;
	padding: 0;
}

.news-card:hover {
	transform: translateY(-5px);
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
}

.news-image-wrapper {
	overflow: hidden;
	max-height: 400px;
}

.news-image {
	width: 100%;
	object-fit: cover;
	transition: transform 0.3s ease;
}

.news-image-wrapper:hover .news-image {
	transform: scale(1.05);
}

.card-text {
	color: #555;
	font-size: 1rem;
	margin-bottom: 15px;
}

.news-footer {
	display: flex;
	justify-content: space-between;
	font-size: 0.85rem;
	color: #888;
	border-top: 1px solid #eee;
	padding-top: 10px;
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

.btn-warning {
	background-color: #f4b400;
	border: none;
	color: var(--dark);
}

.btn-warning:hover {
	background-color: #e0a800;
	color: var(--dark);
}

.btn-danger {
	background-color: #d62828;
	border: none;
}

.btn-danger:hover {
	background-color: #bb2121;
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
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarContent">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarContent">
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0 align-items-lg-center">
					<span class="text-light me-3">Привет, <c:choose>
							<c:when test="${not empty sessionScope.auth}">
                            ${sessionScope.auth.name}
                        </c:when>
							<c:otherwise>Гость</c:otherwise>
						</c:choose>
					</span>

					<c:if test="${not empty sessionScope.auth}">
						<li class="nav-item me-2"><a class="btn btn-outline-light"
							href="NewsPortalController?command=page_add_news">Добавить</a></li>
						<li class="nav-item me-2"><a class="btn btn-outline-light"
							href="NewsPortalController?command=page_user_home">Кабинет</a></li>
					</c:if>

					<li class="nav-item me-2"><a class="btn btn-outline-light"
						href="NewsPortalController?command=page_news_list">Все новости</a></li>
					<li class="nav-item me-2"><a class="btn btn-outline-light"
						href="NewsPortalController?command=do_logout">Выйти</a></li>
				</ul>
			</div>
		</div>
	</header>

	<!-- MAIN -->
	<main class="flex-fill container my-5">
		<!-- Заголовок новости -->
		<h1 class="news-title">${news.title}</h1>

		<!-- Карточка новости -->
		<div class="news-card">
			<!-- Изображение -->
			<div class="news-image-wrapper">
				<img
					src="https://loremflickr.com/400/200/news,technology?lock=${news.id}"
					alt="News image" class="news-image">
			</div>

			<!-- Контент -->
			<div class="card-body p-4">
				<p class="card-text text-muted mb-3">${news.brief}</p>
				<p class="card-text">${news.content}</p>

				<div class="mt-3 d-flex flex-wrap gap-2">
					<a href="javascript:history.back()"
						class="btn btn-secondary btn-sm">← Вернуться назад</a>

					<c:if
						test="${not empty sessionScope.auth && (sessionScope.auth.roleId == 4 || sessionScope.auth.roleId == 2)}">
						<a
							href="NewsPortalController?command=page_edit_news&id=${news.id}"
							class="btn btn-warning btn-sm">Редактировать</a>
						<a
							href="NewsPortalController?command=do_delete_news&id=${news.id}"
							class="btn btn-danger btn-sm"
							onclick="return confirm('Вы действительно хотите удалить эту новость?');">Удалить</a>
					</c:if>
				</div>

				<!-- Дата и автор внизу -->
				<div class="news-footer mt-3">
					<span>Опубликовано: ${news.publishDate}</span>
					<c:if test="${not empty author}">
						<span>Автор: ${author.name} ${author.surname}</span>
					</c:if>
				</div>
			</div>
		</div>
	</main>

	<!-- FOOTER -->
	<footer class="py-4 mt-auto text-center">
		<div class="container">
			<p class="mb-1">&copy; 2025 NewsPortal. Все права защищены.</p>
			<a href="/Controller/about">О нас</a> | <a href="/Controller/contact">Контакты</a>
		</div>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
