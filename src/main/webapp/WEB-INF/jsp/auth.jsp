<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<title>Авторизация</title>
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
					href="NewsPortalController?command=page_registration"
					class="btn btn-outline-light">Регистрация</a>
			</div>
		</div>
	</header>

	<!-- MAIN -->
	<main
		class="flex-fill d-flex justify-content-center align-items-center py-5">
		<div class="card shadow-lg p-4" style="max-width: 400px; width: 100%;">
			<h1 class="h3 mb-4 text-center">Авторизация</h1>

			<!-- Блок ошибки через JSTL -->

			<c:if test="${param.authError eq true}">
				<div class="alert alert-danger" role="alert">Неверный логин
					или пароль.</div>
			</c:if>

			<c:if test="${param.message eq 'need_auth'}">
				<div class="alert alert-warning" role="alert">Пожалуйста,
					авторизуйтесь, чтобы продолжить.</div>
			</c:if>

			<c:if test="${param.after_reg eq true}">
				<div class="alert alert-success" role="alert">Ваша регистрация
					прошла успешно.</div>
			</c:if>

			<form action="NewsPortalController" method="get">
				<input type="hidden" name="command" value="do_auth" />
				<div class="mb-3">
					<input type="email" class="form-control" name="email"
						placeholder="Email" required>
				</div>
				<div class="mb-3">
					<input type="password" class="form-control" name="password"
						placeholder="Пароль" required>
				</div>

				<!-- Запомнить меня и Забыли пароль на одной строке -->
				<div class="mb-3 d-flex justify-content-between align-items-center">
					<div class="form-check">
						<input type="checkbox" class="form-check-input" id="rememberMe"
							name="remember-me"> <label class="form-check-label"
							for="rememberMe">Запомнить меня</label>
					</div>
					<a href="NewsPortalController?command=page_forgot_password">Забыли
						пароль?</a>
				</div>

				<button type="submit" class="btn btn-primary w-100 mb-3">Войти</button>

				<!-- Ссылка на регистрацию -->
				<div class="text-center">
					<a href="NewsPortalController?command=page_registration">Нет
						аккаунта? Зарегистрироваться</a>
				</div>
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
