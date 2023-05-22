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
  </style>
</head>

<body>
  <div class="container">
    <h2>註冊</h2>
    <!-- <form action="intro.html" method="POST">
      <div class="form-group">
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
      </div> -->
      <!-- <div class="form-group"> -->
        <!-- <label for="role">身份：</label>
        <select id="role" name="role">
          <option value="employer">雇主</option>
          <option value="employee">求職者</option>
        </select> -->
      <!-- </div> -->
      <!-- <div class="form-group">
        <button type = "submit">下一步</button>
      </div>
    </form> -->

    <form id="EmployeeRigisterForm" action='${requestUri}' method="GET">
      <div class="form-group">
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
      
      <div class="form-group">
        <button type = "submit">下一步</button>
      </div>
    </form>

    <script>
      document.getElementById("EmployeeRigisterForm").addEventListener("submit", function(event) {
        event.preventDefault(); // 阻止表單提交？
  
        var name = document.getElementById("name").value;
        var account = document.getElementById("account").value;
        var password = document.getElementById("password").value;

        // 在此处使用获取到的值进行后续操作（可能用不到）
        // console.log("姓名: " + name);
        // console.log("帳號: " + account);
        // console.log("密碼: " + password);
        
        
        
        window.location.href = "Intro.jsp"; // 求職者註冊页面
        
      });
    </script>

    <p>已經有帳號了？<a href="Login.jsp">登入</a></p>
  </div>
</body>

</html>