<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>


<head>
 
  

  <title>求職平台</title>
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

      

      .search{
          width: 300px;
          vertical-align: middle;
          white-space: nowrap;
          position: relative;
      }
        
    .search input#search{
        width: 300px;
        height: 50px;
        background: #e7e9ec;
        border: none;
        font-size: 10pt;
        float: left;
        color: #63717f;
        padding-left: 45px;
        -webkit-border-radius: 5px;
        -moz-border-radius: 5px;
        border-radius: 5px;
    }
    .search .icon{
        position: absolute;
        top: 50%;
        margin-left: 13px;
        margin-top: 5px;
        z-index: 1;
        color: #676a70;
        width: 10px;
        border: none;
      }
    .box{
        display: inline-block;
        position: absolute;
        top: 70px;
        left: 420px;
            
    }

    /* 這邊是白框框開始的css */
    .container {
      max-width: 800px;
      margin: 80px auto 0;
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



    

    /* CSS 程式碼 */
  </style>
</head>

<body>
  <div class="header">
    <a class="logo" href="#">
        <img src="1200x630wa.png" alt="">
    </a>
    <nav>
    	<a href="RecommendServlet">工作推薦</a>
    	<a href="CollectServlet">我的收藏</a>
        <a href="ResumeServlet">履歷管理</a>
        <a href="LoginServlet">登出</a>
    </nav>
    
</div>
  

  <main>

    
    <form class="box" action="${pageContext.request.contextPath}/SearchServlet" method="get">
      <div class="search">
        <span class="icon"><i class="fa fa-search"></i></span>
          <input
              type="text"
              id="search"
              name="search"
              placeholder="Search..."
          />
          <span class = "icon"><button type="submit"><image src = "search2.png"></image></button></span>
      </div>
    </form>


    <div class="container">
      <!-- 搜尋結果列表 -->
      <!-- 每個搜尋結果顯示：標題、內容、私訊按鈕、收藏按鈕 -->
      <div id="posts">
      	<c:forEach var="job" items="${jobList}">
          <div class="post">
            <div class="result">
            	<span style="display: none;">${job.jID}</span>
            	<span style="display: none;">${job.cID}</span>
            	${job.jobName}<br>
            	${job.jContent}
            </div>
            <button class="btn-collect" data-action="collect" data-jid="${job.jID}" onclick="collectJob(this)">收藏</button>
  			<button class="btn-message" data-action="message" data-cid="${job.cID}" onclick="message(this)">私訊</button>
           </div>
        </c:forEach>
    
      <script>
	
	      function collectJob(button) {
	    	    var action = button.getAttribute('data-action');
	    	    var jID = button.getAttribute('data-jid');
	    	    
	    	    var xhttp = new XMLHttpRequest();
	    	    
	    	    xhttp.open("GET", "SearchServlet?action=" + encodeURIComponent(action) + "&jID=" + encodeURIComponent(jID), true);
	    	    
	    	    xhttp.onreadystatechange = function() {
	    	      if (xhttp.readyState === 4 && xhttp.status === 200) {
	    	        console.log("Job collected successfully.");
	    	      }
	    	    };
	    	    
	    	    xhttp.send();
	    	  }

	
	      function message(button) {
	    	    var cID = button.getAttribute('data-cid');
	    	  
	    	    window.location.href = "MessageServlet?cID=" + encodeURIComponent(cID);
	    	}  
      </script>
      </div></div >
    
    
    
        
      </div>
    </div>
    
    <div>
      <!-- 搜尋結果列表 -->
      <!-- 每個搜尋結果顯示：標題、公司、地點、私訊按鈕、收藏按鈕 -->
    </div>
  </main>

  <footer>
   
  </footer>
</body>

</html>