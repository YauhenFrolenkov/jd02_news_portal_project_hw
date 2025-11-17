<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="UTF-8">
<title>Регистрация — NewsPortal</title>

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

/* ====== Карточка регистрации ====== */
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

/* ====== Alerts ====== */
.alert {
	border-radius: 10px;
	font-size: 0.9rem;
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
				<a href="NewsPortalController?command=page_auth" class="btn btn-outline-light">Войти</a>
			</div>
		</div>
	</header>

	<!-- MAIN -->
	<main class="flex-fill d-flex justify-content-center align-items-center py-5">
		<div class="card" style="max-width: 460px; width: 100%;">
			<h1>Регистрация</h1>

			<!-- Сообщения -->
			<c:if test="${param.registerError eq true}">
				<div class="alert alert-danger" role="alert">
					Извините. Произошла ошибка регистрации. Попробуйте снова.
				</div>
			</c:if>

			<c:if test="${param.error eq 'exists'}">
				<div class="alert alert-warning">
					Пользователь с таким email уже существует.
				</div>
			</c:if>

			<form action="NewsPortalController" method="post" novalidate>
				<input type="hidden" name="command" value="do_registration" />

				<div class="mb-3">
					<input type="email" name="email" class="form-control" placeholder="Email (логин)" required>
				</div>

				<div class="mb-3">
					<input type="password" name="password" class="form-control" placeholder="Пароль" required>
				</div>

				<div class="mb-3">
					<input type="text" name="name" class="form-control" placeholder="Имя" required>
				</div>

				<div class="mb-4">
					<input type="text" name="surname" class="form-control" placeholder="Фамилия" required>
				</div>

				<button type="submit" class="btn btn-primary w-100 py-2">Зарегистрироваться</button>
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
