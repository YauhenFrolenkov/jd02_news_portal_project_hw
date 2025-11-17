<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ru">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NewsPortal — Главная</title>

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

.navbar-nav .btn {
	font-size: 0.9rem;
	font-weight: 500;
	border-radius: 0;
}

.navbar .btn-outline-light:hover {
	background-color: var(--accent);
	color: var(--dark);
	border-color: var(--accent);
}

.navbar select {
	background: var(--dark);
	color: var(--light);
	border: 1px solid #555;
	font-size: 0.85rem;
	padding: 4px 8px;
	border-radius: 6px;
}

.navbar select:hover {
	background: #111;
}

/* ====== Hero ====== */
.hero {
	position: relative;
	background: linear-gradient(rgba(255, 221, 85, 0.2),
		rgba(255, 255, 255, 0.9)),
		url('https://images.unsplash.com/photo-1522199755839-a2bacb67c546?auto=format&fit=crop&w=1600&q=80')
		center/cover no-repeat;
	padding: 120px 20px;
	text-align: center;
	color: var(--dark);
}

.hero h1 {
	font-size: 3rem;
	font-weight: 800;
	text-transform: uppercase;
	letter-spacing: 1.5px;
	margin-bottom: 20px;
}

.hero p {
	font-size: 1.2rem;
	color: #444;
	max-width: 600px;
	margin: 0 auto;
}

.hero .btn {
	margin-top: 40px;
	background: var(--accent);
	color: var(--dark);
	border: none;
	padding: 12px 32px;
	font-weight: 700;
	text-transform: uppercase;
	letter-spacing: 0.5px;
	transition: all 0.3s ease;
}

.hero .btn:hover {
	background: var(--accent-hover);
	transform: translateY(-3px);
}

/* ====== Карточки ====== */
.card {
	border: none;
	border-radius: 16px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
	transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.card:hover {
	transform: translateY(-5px);
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
}

.card-title {
	font-weight: 600;
	font-size: 1.1rem;
	color: var(--dark);
}

.card-text {
	color: #555;
	font-size: 0.95rem;
}

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

/* ====== Пагинация ====== */
.page-link {
	color: var(--dark);
	border: 1px solid #ddd;
	border-radius: 6px;
	transition: all 0.2s ease;
}

.page-item.active .page-link {
	background-color: var(--accent);
	border-color: var(--accent);
	color: var(--dark);
	font-weight: 600;
}

.page-item:hover .page-link {
	background-color: #fff4b0;
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

	<!-- Header -->
	<header class="navbar navbar-expand-lg navbar-dark py-3">
		<div class="container">
			<a class="navbar-brand" href="NewsPortalController?command=page_main">NewsPortal</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarContent">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarContent">
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0 align-items-lg-center">
					<c:choose>
						<c:when test="${not empty sessionScope.auth}">
							<li class="nav-item me-2"><a class="btn btn-outline-light"
								href="NewsPortalController?command=page_user_home">Кабинет</a></li>
							<li class="nav-item me-2"><a class="btn btn-outline-light"
								href="NewsPortalController?command=page_add_news">Добавить</a></li>
							<li class="nav-item me-2"><a class="btn btn-outline-light"
								href="NewsPortalController?command=do_logout">Выйти</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item me-2"><a class="btn btn-outline-light"
								href="NewsPortalController?command=page_auth">Войти</a></li>
							<li class="nav-item me-2"><a
								class="btn btn-warning text-dark fw-bold"
								href="NewsPortalController?command=page_registration">Регистрация</a></li>
						</c:otherwise>
					</c:choose>

					<!-- Language selector -->
					<li class="nav-item ms-3"><select
						class="form-select form-select-sm"
						onchange="location.href=this.value">
							<option value="/Controller/setLang?lang=ru">Русский</option>
							<option value="/Controller/setLang?lang=en">English</option>
							<option value="/Controller/setLang?lang=de">Deutsch</option>
					</select></li>
				</ul>
			</div>
		</div>
	</header>

	<!-- Hero -->
	<section class="hero">
		<div class="container">
			<h1>Новости без шума</h1>
			<p>Чистые факты. Прямой стиль. Только то, что действительно важно
				— без перегруза и кликов ради кликов.</p>
			<c:if test="${empty sessionScope.auth}">
				<a href="NewsPortalController?command=page_registration"
					class="btn btn-lg">Присоединиться</a>
			</c:if>
		</div>
	</section>

	<!-- Main Content -->
	<main class="container my-5 flex-grow-1">
		<h2 class="fw-bold mb-4 border-bottom pb-2 text-uppercase">Последние
			новости</h2>
		<div class="row g-4">
			<c:forEach var="news" items="${topNews}">
				<div class="col-md-4">
					<div class="card h-100">
						<img
							src="https://loremflickr.com/400/200/news,technology?lock=${news.id}"
							class="card-img-top" alt="${news.title}">
						<div class="card-body d-flex flex-column">
							<h5 class="card-title">${news.title}</h5>
							<p class="card-text flex-grow-1">${news.brief}</p>
							<a
								href="NewsPortalController?command=page_view_news&id=${news.id}"
								class="btn btn-primary mt-auto">Подробнее</a>
						</div>
						<div class="card-footer text-muted small">Опубликовано:
							${news.publishDate}</div>
					</div>
				</div>
			</c:forEach>
		</div>

		<!-- Pagination -->
		<c:if test="${not empty totalPages && totalPages > 1}">
			<nav aria-label="Navigation pages" class="my-5">
				<ul class="pagination justify-content-center">
					<li class="page-item ${currentPage <= 1 ? 'disabled' : ''}"><a
						class="page-link"
						href="NewsPortalController?command=page_main&page=${currentPage - 1}"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>

					<c:forEach var="i" begin="1" end="${totalPages}">
						<li class="page-item ${i == currentPage ? 'active' : ''}"><a
							class="page-link"
							href="NewsPortalController?command=page_main&page=${i}">${i}</a>
						</li>
					</c:forEach>

					<li
						class="page-item ${currentPage >= totalPages ? 'disabled' : ''}">
						<a class="page-link"
						href="NewsPortalController?command=page_main&page=${currentPage + 1}"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a>
					</li>
				</ul>
			</nav>
		</c:if>
	</main>

	<!-- Footer -->
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
