<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ru">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Личный кабинет — NewsPortal</title>

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

/* ====== Общий стиль ====== */
body {
	font-family: 'Inter', sans-serif;
	background: linear-gradient(135deg, #fffef8, #fff4cc);
	color: #222;
	margin: 0;
	padding: 0;
	min-height: 100vh;
}

/* ====== Навигация ====== */
.navbar {
	background-color: var(--dark);
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

/* ====== Hero ====== */
.hero {
	background: linear-gradient(rgba(0, 0, 0, 0.6),
		rgba(0, 0, 0, 0.6)),
		url('https://images.unsplash.com/photo-1581090700227-1e37b190418e?auto=format&fit=crop&w=1600&q=80')
		center/cover no-repeat;
	color: var(--light);
	text-align: center;
	padding: 100px 20px;
}

.hero h1 {
	font-size: 2.5rem;
	font-weight: 800;
	margin-bottom: 10px;
	text-transform: uppercase;
}

.hero p {
	font-size: 1.1rem;
	color: #ddd;
}

/* ====== Карточки новостей ====== */
.card {
	border: none;
	border-radius: 16px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
	transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.card:hover {
	transform: translateY(-5px);
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.12);
}

.card-title {
	font-weight: 600;
	color: var(--dark);
}

.card-text {
	color: #555;
}

.card-footer {
	background: #fafafa;
}

/* ====== Кнопки ====== */
.btn-primary {
	background-color: var(--accent);
	color: var(--dark);
	border: none;
	font-weight: 600;
	text-transform: uppercase;
	letter-spacing: 0.5px;
}

.btn-primary:hover {
	background-color: var(--accent-hover);
	color: var(--dark);
}

/* ====== Footer ====== */
footer {
	background-color: var(--dark);
	color: var(--light);
	font-size: 0.9rem;
}
</style>
</head>

<body class="d-flex flex-column min-vh-100">

	<!-- HEADER -->
	<header class="navbar navbar-expand-lg navbar-dark py-3">
		<div class="container">
			<a class="navbar-brand" href="NewsPortalController?command=page_main">NewsPortal</a>
			<div class="ms-auto d-flex align-items-center">
				<span class="text-light me-3">
					Привет,
					<c:choose>
						<c:when test="${not empty sessionScope.auth}">
							${sessionScope.auth.name}
						</c:when>
						<c:otherwise>Гость</c:otherwise>
					</c:choose>
				</span>

				<a href="NewsPortalController?command=page_main"
					class="btn btn-outline-light me-2">Главная</a>
				<a href="NewsPortalController?command=page_news_list"
					class="btn btn-outline-light me-2">Все новости</a>

				<c:if test="${not empty sessionScope.auth}">
					<a href="NewsPortalController?command=page_user_home"
						class="btn btn-outline-light me-2">Мой кабинет</a>
					<a href="NewsPortalController?command=page_add_news"
						class="btn btn-warning text-dark fw-bold me-2">Добавить</a>
					<a href="NewsPortalController?command=do_logout"
						class="btn btn-outline-light">Выйти</a>
				</c:if>

				<c:if test="${empty sessionScope.auth}">
					<a href="NewsPortalController?command=do_auth"
						class="btn btn-outline-light">Войти</a>
				</c:if>
			</div>
		</div>
	</header>

	<!-- HERO -->
	<section class="hero">
		<div class="container">
			<h1>Личный кабинет</h1>
			<p>Добро пожаловать на NewsPortal! Здесь вы можете управлять своими
				новостями и просматривать последние публикации.</p>
		</div>
	</section>

	<!-- MAIN -->
	<main class="flex-fill container my-5">
		<h2 class="fw-bold mb-4 border-bottom pb-2 text-uppercase">Ваши новости</h2>

		<div class="row g-4">
			<c:choose>
				<c:when test="${not empty topNews}">
					<c:forEach var="news" items="${topNews}">
						<div class="col-md-6 col-lg-4">
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
								<div class="card-footer text-muted small">
									Опубликовано: ${news.publishDate}
								</div>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div class="col-12">
						<div class="alert alert-info shadow-sm">
							Пока нет новостей.
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</main>

	<!-- FOOTER -->
	<footer class="py-4 mt-auto text-center">
		<div class="container">
			<p class="mb-1">&copy; 2025 NewsPortal. Все права защищены.</p>
			<a href="/Controller/about" class="text-decoration-none text-warning">О нас</a> |
			<a href="/Controller/contact" class="text-decoration-none text-warning">Контакты</a>
		</div>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
