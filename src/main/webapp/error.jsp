<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Ошибка — NewsPortal</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;800&display=swap" rel="stylesheet">

  <style>
  :root {
      --accent: #FFD60A;
      --accent-hover: #e6c200;
      --dark: #000;
      --light: #fff;
  }

  body {
      font-family: 'Inter', sans-serif;
      background: linear-gradient(135deg, #fffef8, #fff4cc);
      color: #222;
      margin: 0;
      padding: 0;
      min-height: 100vh;
  }

  /* Navbar */
  .navbar {
      background-color: var(--dark);
  }

  .navbar-brand {
      font-weight: 800;
      font-size: 1.4rem;
      color: var(--accent) !important;
      text-transform: uppercase;
  }

  .btn-outline-light:hover {
      background-color: var(--accent);
      color: var(--dark);
      border-color: var(--accent);
  }

  /* Card */
  .card {
      border: none;
      border-radius: 16px;
      box-shadow: 0 4px 12px rgba(0,0,0,0.05);
      background-color: #fff;
      transition: transform 0.3s ease, box-shadow 0.3s ease;
      padding: 30px;
  }

  .card:hover {
      transform: translateY(-5px);
      box-shadow: 0 8px 20px rgba(0,0,0,0.1);
  }

  /* Заголовок ошибки */
  .display-4 {
      font-weight: 800;
  }

  /* Кнопки */
  .btn-primary {
      background-color: var(--accent);
      color: var(--dark);
      border: none;
      font-weight: 600;
      text-transform: uppercase;
      letter-spacing: 0.5px;
      padding: 10px 20px;
  }

  .btn-primary:hover {
      background-color: var(--accent-hover);
      color: var(--dark);
  }

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
      <div class="ms-auto">
        <a href="NewsPortalController?command=page_main" class="btn btn-outline-light">На главную</a>
      </div>
    </div>
  </header>

  <!-- MAIN -->
  <main class="flex-fill d-flex justify-content-center align-items-center py-5">
    <div class="card text-center" style="max-width: 600px; width: 100%;">
      <h1 class="display-4 text-danger mb-3">Ошибка</h1>
      <p class="lead mb-4">Произошла непредвиденная ошибка при обработке вашего запроса.</p>

      <!-- Сообщение из фильтра -->
      <c:if test="${not empty errorMessage}">
          <p class="text-warning mb-4">${errorMessage}</p>
      </c:if>

      <p class="text-muted mb-4">Попробуйте обновить страницу или вернуться на главную.</p>
      <a href="NewsPortalController?command=page_main" class="btn btn-primary">Вернуться на главную</a>
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

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
