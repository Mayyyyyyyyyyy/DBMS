<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>>
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
    
    .register0-button{
    	width: 100%;
      	padding: 8px;
      	background-color: #4CAF50;
      	color: white;
      	border: none;
     	cursor: pointer;
    }
    
    .register0-button:hover{
     	background-color: #45a049;
    
    
    }
  </style>
</head>

<body>
  <div class="container">
    <h2>註冊</h2><br>
    <form id="roleForm" action='${requestUri}' method="GET">
        <label for="role">身份：</label>
        <label><input type="radio" name="registerRole" value="employee">求職者</label>
        <label><input type="radio" name="registerRole" value="employer">雇主</label>
        <br><br><br>
        <div class="form-group">
        	<input type="submit" value='下一步' class="register0-button">
          </div>
      </form>
    
      <script>
        document.getElementById("roleForm").addEventListener("submit", function(event) {
          event.preventDefault(); // 阻止表單提交？
    
          var selectedRole = document.querySelector('input[name="registerRole"]:checked').value;
          
          if (selectedRole === "employee") {
            window.location.href = "RegisterServletC"; // 重定向到求職者註冊
          } else if (selectedRole === "employer") {
            window.location.href = "RegisterServletE"; // 重定向到雇主註冊
          }
        });
      </script>
    <p>已經有帳號了？<a href="LoginServlet">登入</a></p>
  </div>
</body>

</html>