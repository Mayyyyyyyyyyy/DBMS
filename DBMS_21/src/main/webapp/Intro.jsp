<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <title>個人資料</title>
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
    
     .register-button{
    	width: 100%;
      	padding: 8px;
      	background-color: #4CAF50;
      	color: white;
      	border: none;
     	cursor: pointer;
    }
    
    .register-button:hover{
     	background-color: #45a049;
    
    
    }
  </style>
</head>

<body>
  <div class="container">
    <h2>基本資料</h2>
    <!-- zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz -->
    <form action='${requestUri}' method='get'>
      <div class="form-group">
        <label for="gender">性別:</label>
        
        <select id="gender" name="gender">
        <option value="">請選擇</option>
        <option value="女">女</option>
        <option value="男">男</option>
        <option value="其他">其他</option>
        <option value="不願透露">不願透露</option>
    </select>
      </div>
      <div class="form-group">
        <label for="age">年齡:</label>
        <input type="text" id="age" name="age" required>
      </div>
      <div class="form-group">
        <label for="grade">年級:</label>
        <select id="grade" name="grade">
            <option value="">請選擇</option>
            <option value="大一">大一</option>
            <option value="大二">大二</option>
            <option value="大三">大三</option>
            <option value="大四">大四</option>
            <option value="碩士">碩士</option>
            <option value="博士">博士</option>
        </select>
      </div>
      <div class="form-group">
        <label for="major">科系:</label>
        <input type="text" id="major" name= "major" required>
       
      </div>

      <div class="form-group">
        <label for="email">email：</label>
        <input type="email" id="email" name="email" required>
      </div>

      <div class="form-group">
        <input type="submit" value='註冊' class="register-button">
      </div>
    </form>
    <p>已經有帳號了？<a href="LoginServlet">登入</a></p>
  </div>
</body>

</html>