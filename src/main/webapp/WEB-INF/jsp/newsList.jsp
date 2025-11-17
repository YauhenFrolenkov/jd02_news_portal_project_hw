<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NewsPortal — Новости</title>

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

/* ====== Контент ====== */
main {
	padding-top: 60px;
	padding-bottom: 60px;
}

.alert {
	border-radius: 12px;
	font-weight: 500;
}

/* ====== Карточки ====== */
.card {
	border: none;
	border-radius: 16px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
	transition: transform 0.3s ease, box-shadow 0.3s ease;
	background-color: #fff;
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

.card-footer {
	background-color: #f8f9fa;
	border-top: none;
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
						href="NewsPortalController?command=do_logout">Выйти</a></li>
				</ul>
			</div>
		</div>
	</header>


	<!-- MAIN -->
	<main class="container flex-fill">

		<c:if test="${param.error == 'unauthorized'}">
			<div class="alert alert-danger text-center shadow-sm">У вас нет
				прав для выполнения этого действия.</div>
		</c:if>

		<c:if test="${param.error == 'true'}">
			<div class="alert alert-warning text-center shadow-sm">Произошла
				ошибка при выполнении операции. Попробуйте позже.</div>
		</c:if>

		<h2 class="fw-bold mb-4 border-bottom pb-2 text-uppercase text-center">Все
			новости</h2>

		<div class="row g-4">
			<c:forEach var="news" items="${newsList}">
				<div class="col-md-6 col-lg-4">
					<div class="card h-100">
						<img
							src="https://loremflickr.com/400/200/news,technology?lock=${news.id}"
							class="card-img-top" alt="${news.title}">
						<div class="card-body d-flex flex-column">
							<h5 class="card-title">${news.title}</h5>
							<p class="card-text flex-grow-1">${news.brief}</p>

							<div class="d-flex justify-content-between mt-auto">
								<a
									href="NewsPortalController?command=page_view_news&id=${news.id}"
									class="btn btn-primary btn-sm">Читать</a>

								<c:if
									test="${not empty sessionScope.auth 
									&& (sessionScope.auth.roleId == 4 
									|| sessionScope.auth.roleId == 2)}">
									<div class="d-flex gap-1">
										<a
											href="NewsPortalController?command=page_edit_news&id=${news.id}"
											class="btn btn-warning btn-sm">Редактировать</a> <a
											href="NewsPortalController?command=do_delete_news&id=${news.id}"
											class="btn btn-danger btn-sm"
											onclick="return confirm('Вы действительно хотите удалить эту новость?');">
											Удалить </a>
									</div>
								</c:if>
							</div>
						</div>
						<div class="card-footer text-muted small">Опубликовано:
							${news.publishDate}</div>
					</div>
				</div>
			</c:forEach>
		</div>

		<!-- PAGINATION -->
		<c:if test="${page.totalPages > 1}">
			<nav aria-label="News pagination" class="mt-5">
				<ul class="pagination justify-content-center">

					<li class="page-item ${page.page == 1 ? 'disabled' : ''}"><a
						class="page-link"
						href="NewsPortalController?command=page_news_list&page=${page.page - 1}">
							&laquo; </a></li>

					<c:forEach var="i" begin="1" end="${page.totalPages}">
						<li class="page-item ${i == page.page ? 'active' : ''}"><a
							class="page-link"
							href="NewsPortalController?command=page_news_list&page=${i}">
								${i} </a></li>
					</c:forEach>

					<li
						class="page-item ${page.page == page.totalPages ? 'disabled' : ''}">
						<a class="page-link"
						href="NewsPortalController?command=page_news_list&page=${page.page + 1}">
							&raquo; </a>
					</li>
				</ul>
			</nav>
		</c:if>

		<p class="text-center text-muted mt-3">Страница ${page.page} из
			${page.totalPages}</p>
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
