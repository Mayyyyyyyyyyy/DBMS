<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <title>登入</title>
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
    
    .login-button{
    	width: 100%;
      	padding: 8px;
      	background-color: #4CAF50;
      	color: white;
      	border: none;
     	cursor: pointer;
    }
    
    .login-button:hover{
     	background-color: #45a049;
    
    
    }
  </style>
</head>

<body>
  <div class="container">
    <h2>登入</h2>
    <form action='${requestUri}' method='get'>
      <div class="form-group">
        <label for="account">帳號:</label>
        <input type="text" id="loginAccount" name="loginAccount" required>
      </div>
      <div class="form-group">
        <label for="password">密碼:</label>
        <input type="password" id="loginPassword" name="loginPassword" required>
      </div>
        <label for="role">身份：</label>
        <label><input type="radio" name="role" value="employee">求職者</label>
        <label><input type="radio" name="role" value="employer">雇主</label>
        <br><br><br>
        
        <div class="form-group">
            <input type = "submit" value='登入' class="login-button">
          </div>
      </form>
    </form>
    
    <p>還沒有帳號？<a href="Register0Servlet">註冊</a></p>
  </div>

  <script>
  </script>
</body>

</html>