<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>刊登職位</title>
    <style>
        .header{
             display: flex;
            background-color: rgb(184, 176, 176);
        }

        .header nav{
            display: flex;
            width: 100%;
            justify-content: flex-end;
            line-height: 25px;
            font-size: 0px;
        }

        .header nav a{
            display: inline-block;
            padding: 10px;
            color: blue;
            font-size: 16px;
        }
        
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
    
    .supply-button{
    	width: 100%;
      	padding: 8px;
      	background-color: #4CAF50;
      	color: white;
      	border: none;
     	cursor: pointer;
    }
    
    .supply-button:hover{
     	background-color: #45a049;
    }
  

</style>
</head>
<body>
    <div class="header">
        <a class="logo" href="#">
            <img src="1200x630wa.png" alt="">
        </a>
        <nav>
            <a href="CompanyServlet">首頁</a>
            <a href="SupplyServlet">刊登職位</a>
            <a href="LoginServlet">登入</a>
            <a href="Register0Servlet">註冊</a>
        </nav>
        
    </div>
    <div class="container">
    <h2>刊登職位</h2>
    <!-- zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz -->
    <form id="supplyForm" action="${requestUri}" method="GET">
  
    <label for="jobTitle">工作名稱:</label>
    <input type="text" id="jobTitle" name="jobTitle" required><br><br>
    
    
    <label for="description">工作簡介:</label>
    <textarea id="description" name="description" required></textarea><br><br>
    
    <div class="form-group">
        <input type = "submit" value='刊登' class="supply-button">
    </div>
  </form>



  <script>
  </script>
</body>
</html>