<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>

<head>
  <title>�n�J</title>
  <style>
    body {
      font-family: Arial, sans-serif;
    }

    .container {
      width: 300px;
      margin: 0 auto;
    }

    .form-group {
      margin-bottom: 15px;
    }

    .form-group label {
      display: block;
      margin-bottom: 5px;
    }

    .form-group input {
      width: 100%;
      padding: 5px;
    }

    .form-group button {
      width: 100%;
      padding: 8px;
      background-color: #4CAF50;
      color: white;
      border: none;
      cursor: pointer;
    }

    .form-group button:hover {
      background-color: #45a049;
    }
  </style>
</head>

<body>
  <div class="container">
    <h2>�n�J</h2>
    <form>
      <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" required>
      </div>
      <div class="form-group">
        <label for="password">�K�X:</label>
        <input type="password" id="password" required>
      </div>
      <div class="form-group">
        <button type="submit">�n�J</button>
      </div>
    </form>
    <p>�٨S���b���H<a href="Register.jsp">���U</a></p>
  </div>
</body>

</html>