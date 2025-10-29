<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Ошибка</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light d-flex flex-column min-vh-100">

  <!-- HEADER -->
  <header class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
      <a class="navbar-brand" href="/Controller/index">NewsPortal</a>
      <div class="ms-auto">
        <a href="NewsPortalController?command=page_main" class="btn btn-outline-light">На главную</a>
      </div>
    </div>
  </header>

  <!-- MAIN -->
  <main class="flex-fill d-flex justify-content-center align-items-center py-5">
    <div class="card shadow-lg p-4 text-center" style="max-width: 600px; width: 100%;">
      <h1 class="display-4 text-danger mb-3">Ошибка</h1>
      <p class="lead mb-4">Произошла непредвиденная ошибка при обработке вашего запроса.</p>
      
      
      <!-- Тут выводим сообщение из фильтра -->
      <c:if test="${not empty errorMessage}">
          <p class="text-warning mb-4">${errorMessage}</p>
      </c:if>
      
      <p class="text-muted mb-4">Попробуйте обновить страницу или вернуться на главную.</p>
      <a href="NewsPortalController?command=page_main" class="btn btn-primary">Вернуться на главную</a>
    </div>
  </main>

  <!-- FOOTER -->
  <footer class="bg-dark text-light text-center py-3 mt-auto">
    &copy; 2025 NewsPortal. Все права защищены.
  </footer>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
