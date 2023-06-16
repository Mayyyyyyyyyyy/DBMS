<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>公司主頁</title>
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
            <a href="CompanyServlet">首頁</a>
            <a href="SupplyServlet">刊登職位</a>
            <a href="LoginServlet">登入</a>
            <a href="Register0Servlet">註冊</a>
						<a href="Chat1.jsp">私訊</a>

            
        </nav>
        
    </div>
    <h2>公司主頁</h2>
    <div class="container">
        <c:if test="${not empty jobList}">
			<c:forEach var="job" items="${jobList}">
				<div class="post">
					<div class="result">
						${job.jobName}<br>${job.jContent}
					</div>
				</div>
			</c:forEach>
		</c:if>
      
        <script>
    
          //var postsData = [
            //{
              //jobtitle: '行政助理',
            //   salary: '180 /hr',
            //   request:'文書處理能力',
              //content: '詳細內容。'
            //},
            //{
              //jobtitle: '前端工程師',
            
              //content: '詳細內容。'
             
            //}
          //];
      
          //for (var i = 0; i < postsData.length; i++) {
            //var post = postsData[i];

            //var postElement = document.createElement('div');
            //postElement.className = 'post';

            //var jobtitleElement = document.createElement('div');
            //jobtitleElement.className = 'jobtitle';
            //jobtitleElement.textContent = post.jobtitle;
            //postElement.appendChild(jobtitleElement);

            //var contentElement = document.createElement('div');
            //contentElement.className = 'content';
            //contentElement.textContent = post.content;
            //postElement.appendChild(contentElement);

            //ocument.getElementById('posts').appendChild(postElement);
          //}
        </script>
        </div></div >
    
</body>
</html>