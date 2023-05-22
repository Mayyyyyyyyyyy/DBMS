<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
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
/*以下為測試用*/
.container {
      max-width: 800px;
      margin: 0 auto;
      padding: 10px;
      background-color: #fff;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    .post {
    max-width: 600px;
    border: 1px solid #ccc;
    border-radius: 5px;
    padding: 10px;
    margin-bottom: 15px;
    margin-left: 100px; 
    }

 .post textarea {
 width: 100%;
    height: 500px;
 border: none;
    resize: none;
    }

    .post button {
      display: block;
      margin-top: 20px;
      padding: 5px 10px;
      background-color: #467adc;
      color: white;
      border: none;
      border-radius: 3px;
      cursor: pointer;
    }

    .post button.clicked {
      background-color: #e53b69;
    }
    
    </style>
</head>
<body>
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
    <div class="container">
        <!-- 搜尋結果列表 -->
        <!-- 每個搜尋結果顯示：標題、公司、地點、私訊按鈕、收藏按鈕 -->
        <div id="posts">
      
        <script>
    
          var postsData = [
            {
              jobtitle: '行政助理',
            //   salary: '180 /hr',
            //   request:'文書處理能力',
              content: '詳細內容。'
            },
            {
              jobtitle: '前端工程師',
            //   salary: '50000',
            //   request:'資工系、資管系',
              content: '詳細內容。'
             
            }
          ];
      
          for (var i = 0; i < postsData.length; i++) {
            var post = postsData[i];
      
          
            var postElement = document.createElement('div');
            postElement.className = 'post';
      
           
            var jobtitleElement = document.createElement('div');
            jobtitleElement.className = 'jobtitle';
            jobtitleElement.textContent = post.jobtitle;
            postElement.appendChild(jobtitleElement);
      
            // var salaryElement = document.createElement('div');
            // salaryElement.className = 'salary';
            // salaryElement.textContent = post.salary;
            // postElement.appendChild(salaryElement);

            // var requestElement = document.createElement('div');
            // requestElement.className = 'request';
            // requestElement.textContent = post.request;
            // postElement.appendChild(requestElement);
      
            var contentElement = document.createElement('div');
            contentElement.className = 'content';
            contentElement.textContent = post.content;
            postElement.appendChild(contentElement);
    
            var buttonElement = document.createElement('button');
          buttonElement.textContent = '收藏';
          postElement.appendChild(buttonElement);

          // Add click event listener to the button
        buttonElement.addEventListener('click', function() {
        // Toggle the "clicked" class on the button
        this.classList.toggle('clicked');
        });


          var buttonElement1 = document.createElement('button');
          buttonElement1.textContent = '私訊';
          postElement.appendChild(buttonElement1);

					buttonElement1.addEventListener('click', function() {
            window.location.href = 'Chat1.jsp';
        });
    
            document.getElementById('posts').appendChild(postElement);
          }
        </script>
        </div></div >
    
</body>
</html>