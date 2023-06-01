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
        <!-- <button type = "submit">註冊</button> -->
        <input type="submit" value='註冊' class="register2-button">
      </div>
    </form>
 <p>已經有帳號了？<a href="Login.jsp">登入</a></p>
  </div>
</body>
    <script>
      document.getElementById("EmployerRigisterForm").addEventListener("submit", function(event) {
        event.preventDefault(); // 阻止表單提交？
  
        var name = document.getElementById("name").value;
        var account = document.getElementById("account").value;
        var password = document.getElementById("password").value;

        // 在此处使用获取到的值进行后续操作（可能用不到）
        // console.log("姓名: " + name);
        // console.log("帳號: " + account);
        // console.log("密碼: " + password);
        
        // 先存到本地端，（之後修改存到資料庫）
        // localStorage.setItem("registeredAccount", account);
        // localStorage.setItem("registeredPassword", password);
        
        // window.location.href = "intro.html"; // 求職者註冊页面
        
      });
    </script>

   

</html>