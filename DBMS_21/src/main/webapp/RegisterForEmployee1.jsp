<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <title>註冊</title>
  
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
    
    .register1-button{
    	width: 100%;
      	padding: 8px;
      	background-color: #4CAF50;
      	color: white;
      	border: none;
     	cursor: pointer;
    }
    
    .register1-button:hover{
     	background-color: #45a049;
    
    
    }
  </style>
</head>

<body>
  <div class="container">
    <h2>註冊</h2>

    <form id="EmployeeRigisterForm" action='${requestUri}' method='get'>
      <div class="form-group">
        <label for="name">姓名:</label>
        <input type="text" id="name" name ="employeeName" required>
      </div>
      <div class="form-group">
        <label for="account">帳號:</label>
        <input type="text" id="account" name="employeeAccount" required>
      </div>
      <div class="form-group">
        <label for="password">密碼:</label>
        <input type="password" id="password" name="employeePassword" required>
      </div>
      
      <div class="form-group">
        <input type = "submit" value='下一步' class="register1-button">
      </div>
    </form>

    <script>
       
    </script>

    <p>已經有帳號了？<a href="LoginServlet">登入</a></p>
  </div>
</body>

</html>