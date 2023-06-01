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
    <!-- zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz -->
    <form action='${requestUri}' method='get'>
      <div class="form-group">
        <label for="account">帳號:</label>
        <input type="text" id="loginAccount" name="loginAccount" required>
      </div>
      <div class="form-group">
        <label for="password">密碼:</label>
        <input type="text" id="loginPassword" name="loginPassword" required>
      </div>
      <!-- <div class="form-group">
        <button type="submit">登入</button>
      </div> -->
      <!-- <form id="roleForm" action='${requestUri}' method="GET"> -->
        <label for="role">身份：</label>
        <label><input type="radio" name="role" value="employee">求職者</label>
        <label><input type="radio" name="role" value="employer">雇主</label>
        <br><br><br>
        <!-- <input type="submit" value="下一步"> -->
        
        <div class="form-group">
            <input type = "submit" value='登入' class="login-button">
          </div>
      </form>
    
      <!-- <script>
        document.getElementById("roleForm").addEventListener("submit", function(event) {
          event.preventDefault(); // 阻止表單提交？
    
          var selectedRole = document.querySelector('input[name="role"]:checked').value;
          
          if (selectedRole === "employee") {
            window.location.href = "Homepage.html"; // 求職者首頁
          } else if (selectedRole === "employer") {
            window.location.href = "HomepageForCompany.html"; // 重定向到雇主註冊页面
          }
        });
      </script> -->
    </form>
    
    <p>還沒有帳號？<a href="Register0.jsp">註冊</a></p>
  </div>

  <script>
    document.getElementById("loginForm").addEventListener("submit", function(event) {
      event.preventDefault(); // 阻止表單提交？
  
      
    
      var loginAccount = document.getElementById("loginAccount").value;
      var loginPassword = document.getElementById("loginPassword").value;
      var selectedRole = document.querySelector('input[name="role"]:checked').value;


//      if (selectedRole === "employee") {
 //           window.location.href = "Homepage.jsp"; // 求職者首頁
 //         } else if (selectedRole === "employer") {
  //          window.location.href = "HomepageForCompany.jsp"; // 重定向到雇主註冊页面
   //       }
    // 先將儲存在本地的帳號密碼拿來驗證（
    // var registeredAccount = localStorage.getItem("registeredAccount");
    // var registeredPassword = localStorage.getItem("registeredPassword");

  
      // 帳號密碼驗證(這邊要再改，或是可以在其他地方驗證？)
    //   if (loginAccount === registeredAccount && loginPassword === registeredPassword) {
    //   window.location.href = "homepage.html"; // success
    // } else {
    //   alert("您的帳號或密碼錯誤"); // 登录失败
    // }
  });
  </script>
</body>

</html>