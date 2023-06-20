<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <title>註冊</title>
  <script>
    function goToNextPage() {
      
      var inputText = document.getElementById("name").value;
      var inputText1 = document.getElementById("account").value;
      var inpurText2 = document.getElementById("password").value;


      window.location.href = "intro.html";
    }
  </script>
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
    
    .register2-button{
    	width: 100%;
      	padding: 8px;
      	background-color: #4CAF50;
      	color: white;
      	border: none;
     	cursor: pointer;
    }
    
    .register2-button:hover{
     	background-color: #45a049;
    }
  </style>
</head>

<body>
  <div class="container">
    <h2>註冊</h2>

    <form id="EmployerRigisterForm" action='${requestUri}' method="GET">
      <div class="form-group">
        <label for="name">姓名:</label>
        <input type="text" id="name" name="employerName" required>
      </div>
      <div class="form-group">
        <label for="account">帳號:</label>
        <input type="text" id="account" name="employerAccount" required>
      </div>
      <div class="form-group">
        <label for="password">密碼:</label>
        <input type="password" id="password" name="employerPassword" required>
      </div>
      
      <div class="form-group">
        <input type="submit" value='註冊' class="register2-button">
      </div>
    </form>
 <p>已經有帳號了？<a href="Login.jsp">登入</a></p>
  </div>
</body>
    <script>
    </script>

   

</html>