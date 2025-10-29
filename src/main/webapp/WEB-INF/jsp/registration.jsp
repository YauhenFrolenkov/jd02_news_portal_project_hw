<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<title>Регистрация</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light d-flex flex-column min-vh-100">

	<!-- HEADER -->
	<header class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand" href="/Controller/index">NewsPortal</a>
			<div class="ms-auto">
				<a href="NewsPortalController?command=page_main"
					class="btn btn-outline-light me-2">На главную</a> <a
					href="NewsPortalController?command=page_auth"
					class="btn btn-outline-light">Войти</a>
			</div>
		</div>
	</header>

	<!-- MAIN -->
	<main
		class="flex-fill d-flex justify-content-center align-items-center py-5">
		<div class="card shadow-lg p-4" style="max-width: 500px; width: 100%;">
			<h1 class="h4 mb-4 text-center">Регистрация пользователя</h1>

			<!-- Сообщения -->

			<c:if test="${param.registerError eq true}">
				<div class="alert alert-danger"role="alert">
                    Извините. Произошла ошибка регистрации. Попробуйте снова. 
            	</div>
			</c:if>

			<c:if test="${param.error eq 'exists'}">
				<div class="alert alert-warning">Пользователь с таким email
					уже есть.</div>
			</c:if>


			<form action="NewsPortalController" method="post" novalidate>
				<input type="hidden" name="command" value="do_registration" /> 
				<div class="mb-3">
					<input type="email" name="email" class="form-control"
						placeholder="Email (логин)" required>
				</div>

				<div class="mb-3">
					<input type="password" name="password" class="form-control"
						placeholder="Пароль" required>
				</div>

				<div class="mb-3">
					<input type="text" name="name" class="form-control"
						placeholder="Имя" required>
				</div>

				<div class="mb-3">
					<input type="text" name="surname" class="form-control"
						placeholder="Фамилия" required>
				</div>

				<button type="submit" class="btn btn-primary w-100">Зарегистрироваться</button>
			</form>
		</div>
	</main>

	<!-- FOOTER -->
	<footer class="bg-dark text-light text-center py-3 mt-auto">
		&copy; 2025 NewsPortal. Все права защищены. </footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>