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
    <!-- <form action="register.html" method="POST"> -->
      <!-- <div class="form-group">
        <label for="name">姓名:</label>
        <input type="text" id="name" required>
      </div>
      <div class="form-group">
        <label for="account">帳號:</label>
        <input type="text" id="account" required>
      </div>
      <div class="form-group">
        <label for="password">密碼:</label>
        <input type="password" id="password" required>
      </div>
      <div class="form-group"> -->
        <!-- <label for="role">身份：</label>
        <label><input type="radio" name="role" value="emlpoyee">求職者</label>
        <label><input type="radio" name="role" value="employer">雇主</label> -->
        <!-- <select id="role" name="role">

          <option value="employer">雇主</option>
          <option value="employee">求職者</option>
        </select> -->
      <!-- </div>
      <div class="form-group">
        <button type = "submit">下一步</button>
      </div>
    </form> -->
    <!-- zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz -->
    <form id="roleForm" action='${requestUri}' method="GET">
        <label for="role">身份：</label>
        <label><input type="radio" name="registerRole" value="employee">求職者</label>
        <label><input type="radio" name="registerRole" value="employer">雇主</label>
        <br><br><br>
        <!-- <input type="submit" value="下一步"> -->
        <div class="form-group">
        	<input type="submit" value='下一步' class="register0-button">
            <!--  <button type = "submit">下一步</button>-->
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