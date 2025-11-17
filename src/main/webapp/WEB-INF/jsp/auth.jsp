<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<title>Авторизация — NewsPortal</title>

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
	display: flex;
	flex-direction: column;
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

/* ====== Карточка авторизации ====== */
.card {
	border: none;
	border-radius: 16px;
	box-shadow: 0 6px 18px rgba(0, 0, 0, 0.1);
	padding: 2rem;
	background-color: #fff;
}

.card h1 {
	font-weight: 700;
	text-align: center;
	margin-bottom: 1.5rem;
	text-transform: uppercase;
	color: var(--dark);
}

/* ====== Alerts ====== */
.alert {
	border-radius: 10px;
	font-size: 0.9rem;
}

/* ====== Кнопки ====== */
.btn-primary {
	background-color: var(--accent);
	color: var(--dark);
	border: none;
	font-weight: 600;
	text-transform: uppercase;
	letter-spacing: 0.5px;
	transition: all 0.3s ease;
}

.btn-primary:hover {
	background-color: var(--accent-hover);
	color: var(--dark);
	transform: translateY(-2px);
}

a {
	color: var(--dark);
	font-size: 0.9rem;
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

/* ====== Footer ====== */
footer {
	background-color: var(--dark);
	color: var(--light);
	font-size: 0.9rem;
}
</style>
</head>

<body>

	<!-- HEADER -->
	<header class="navbar navbar-expand-lg navbar-dark py-3">
		<div class="container">
			<a class="navbar-brand" href="NewsPortalController?command=page_main">NewsPortal</a>
			<div class="ms-auto">
				<a href="NewsPortalController?command=page_main" class="btn btn-outline-light me-2">На главную</a>
				<a href="NewsPortalController?command=page_registration" class="btn btn-outline-light">Регистрация</a>
			</div>
		</div>
	</header>

	<!-- MAIN -->
	<main class="flex-fill d-flex justify-content-center align-items-center py-5">
		<div class="card" style="max-width: 440px; width: 100%;">
			<h1>Авторизация</h1>

			<!-- Сообщения -->
			<c:if test="${param.authError eq true}">
				<div class="alert alert-danger" role="alert">
					Неверный логин или пароль.
				</div>
			</c:if>

			<c:if test="${param.message eq 'need_auth'}">
				<div class="alert alert-warning" role="alert">
					Пожалуйста, авторизуйтесь, чтобы продолжить.
				</div>
			</c:if>

			<c:if test="${param.after_reg eq true}">
				<div class="alert alert-success" role="alert">
					Ваша регистрация прошла успешно. Теперь можете войти.
				</div>
			</c:if>

			<form action="NewsPortalController" method="get" novalidate>
				<input type="hidden" name="command" value="do_auth" />

				<div class="mb-3">
					<input type="email" class="form-control" name="email" placeholder="Email" required>
				</div>

				<div class="mb-3">
					<input type="password" class="form-control" name="password" placeholder="Пароль" required>
				</div>

				<div class="mb-3 d-flex justify-content-between align-items-center">
					<div class="form-check">
						<input type="checkbox" class="form-check-input" id="rememberMe" name="remember-me">
						<label class="form-check-label" for="rememberMe">Запомнить меня</label>
					</div>
					<a href="NewsPortalController?command=page_forgot_password">Забыли пароль?</a>
				</div>

				<button type="submit" class="btn btn-primary w-100 py-2 mb-3">Войти</button>

				<div class="text-center">
					<a href="NewsPortalController?command=page_registration">Нет аккаунта? Зарегистрироваться</a>
				</div>
			</form>
		</div>
	</main>

	<!-- FOOTER -->
	<footer class="py-4 text-center mt-auto">
		<div class="container">
			<p class="mb-1">&copy; 2025 NewsPortal</p>
			<a href="/Controller/about" class="text-decoration-none text-warning">О нас</a> |
			<a href="/Controller/contact" class="text-decoration-none text-warning">Контакты</a>
		</div>
	</footer>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
