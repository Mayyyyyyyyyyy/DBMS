
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>個人履歷</title>
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
    .container {
      max-width: 600px;
      margin: 0 auto;
      padding: 20px;
    }
    body {
      font-family: Arial, sans-serif;
    }
    h1 {
      color: #333;

    }
    label {
      
      display: block;
      margin-bottom: 5px;
    }
    input, textarea {
      width: 100%;
      padding: 5px;
      margin-bottom: 10px;
    }
    button {
      padding: 10px 20px;
      background-color: #333;
      color: #fff;
      border: none;
      cursor: pointer;
    }
  </style>
</head>
<body>
  <header>
    
    <div class="header">
      <a class="logo" href="#">
          <img src="1200x630wa.png" alt="">
      </a>
      <nav>
        <a href="Homepage.jsp">首頁</a>
        <a href="Resume.jsp">履歷管理</a>
        <a href="Login.jsp">登入</a>
        <a href="Register0.jsp">註冊</a>
        <a href="Love.jsp">我的收藏</a>
      </nav>
      
  </div>
  </header>
  
  <h1>個人履歷</h1>
  <div class='container'>

  <!-- //zzzzzzzzzzzzzzzzzz -->
  <!-- Gender Age Education：教育程度(下拉式選單）年級 科系
 -->
  <!-- <%// 從資料庫裡面找
    String name = null;
    String gender = null;
    int age = 0;
    String grade =null;
    String major = null;
    String email = null;


%> -->

<h2>基本資訊</h2>
<!-- 這邊會將註冊時填入的資料從資料庫當中找出來放在這邊 -->
<label for="name">姓名：</label>
<input type="text" id="name" name="name" value="<%= name %>">
<label for="gender">性別：</label>
<input type="text" id="gender" name="gender" value="<%= gender %>">
<label for="age">年齡:</label>
<input type="text" id="age" name="age" value="<%= age %>">
<label for="grade">年級:</label>
<input type="text" id="grade" name="grade" value="<%= grade %>">
<label for="major">科系:</label>
<input type="text" id="major" name="major" value="<%= major %>">
<label for="email">email：</label>
<input type="email" id="email" name="email" value="<%= email %>">


  <form action='${requestUri}' method='get'>
    
    
    <label for="phone">電話號碼：</label>
    <input type="tel" id="phone" name="phone">
    <!-- <label for="address">地址：</label>
    <textarea id="address" name="address"></textarea> -->

    <label for="intro">簡介：</label>
    <textarea id="intro" name="intro"></textarea>
  
  
    <!-- <h2>技能</h2> -->
    <label for="skills">技能：</label>
    <textarea id="skills" name="skills"></textarea>
  
    <button type="submit">儲存</button>
  </form>
</div>
  
  <footer>
    <p>&copy; 2023 Your Name</p>
  </footer>
</body>
</html>