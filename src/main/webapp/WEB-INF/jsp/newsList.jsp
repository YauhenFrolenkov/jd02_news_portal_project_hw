<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<title>Список новостей</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light d-flex flex-column min-vh-100">

	<!-- HEADER -->
	<header class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand" href="/Controller/index">NewsPortal</a>
			<div class="ms-auto d-flex align-items-center">
				<span class="text-light me-3"> Привет, <c:choose>
						<c:when test="${not empty sessionScope.auth}">
                        ${sessionScope.auth.name}
                    </c:when>
						<c:otherwise>
                        Гость
                    </c:otherwise>
					</c:choose>
				</span>
				<c:if test="${not empty sessionScope.auth}">
					<a href="NewsPortalController?command=page_add_news"
						class="btn btn-outline-light me-2">Добавить новость</a>
					<a href="NewsPortalController?command=page_user_home"
						class="btn btn-outline-light me-2">Мой кабинет</a>
				</c:if>

				<a href="NewsPortalController?command=page_auth"
					class="btn btn-outline-light">Выйти</a>
			</div>
		</div>
	</header>

	<!-- MAIN -->
	<main class="flex-fill container py-5">

		<div class="row g-4">
			<c:forEach var="news" items="${newsList}">
				<div class="col-md-6 col-lg-4">
					<div class="card h-100 shadow-sm">
						<img src="https://picsum.photos/400/200?random=${news.id}"
							class="card-img-top" alt="${news.title}">
						<div class="card-body d-flex flex-column">
							<h5 class="card-title">${news.title}</h5>
							<p class="card-text text-muted flex-grow-1">${news.brief}</p>
							<div class="d-flex justify-content-between mt-auto">
								<a
									href="NewsPortalController?command=page_view_news&id=${news.id}"
									class="btn btn-primary btn-sm">Читать</a>
								<c:if test="${not empty sessionScope.auth}">
									<a
										href="NewsPortalController?command=page_edit_news&id=${news.id}"
										class="btn btn-warning btn-sm">Редактировать</a>
									<a
										href="NewsPortalController?command=do_delete_news&id=${news.id}"
										class="btn btn-danger btn-sm"
										onclick="return confirm('Вы действительно хотите удалить эту новость?');">Удалить</a>
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

					<!-- Кнопка "Предыдущая" -->
					<li class="page-item ${page.page == 1 ? 'disabled' : ''}">
						<a class="page-link"
						href="NewsPortalController?command=page_news_list&page=${page.page - 1}">
							&laquo;
						</a>
					</li>

					<!-- Номера страниц -->
					<c:forEach var="i" begin="1" end="${page.totalPages}">
						<li class="page-item ${i == page.page ? 'active' : ''}">
							<a class="page-link"
							href="NewsPortalController?command=page_news_list&page=${i}">
								${i}
							</a>
						</li>
					</c:forEach>

					<!-- Кнопка "Следующая" -->
					<li class="page-item ${page.page == page.totalPages ? 'disabled' : ''}">
						<a class="page-link"
						href="NewsPortalController?command=page_news_list&page=${page.page + 1}">
							&raquo;
						</a>
					</li>
				</ul>
			</nav>
		</c:if>

		<p class="text-center text-muted mt-2">Страница ${page.page}
			из ${page.totalPages}</p>

	</main>

	<!-- FOOTER -->
	<footer class="bg-dark text-light text-center py-3 mt-auto">
		&copy; 2025 NewsPortal. Все права защищены. </footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
