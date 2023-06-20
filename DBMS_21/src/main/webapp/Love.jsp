<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>我的收藏</title>
<style>
.header {
	display: flex;
	background-color: rgb(184, 176, 176);
}

.header nav {
	display: flex;
	width: 100%;
	justify-content: flex-end;
	line-height: 25px;
	font-size: 0px;
}

.header nav a {
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
		<a class="logo" href="#"> <img src="1200x630wa.png" alt="">
		</a>
		<nav>
			<a href="RecommendServlet">工作推薦</a>
			<a href="SearchServlet">職缺搜尋</a>
        	<a href="ResumeServlet">履歷管理</a>
        	<a href="LoginServlet">登出</a>
		</nav>

	</div>
	<div class="container">
		<!-- 搜尋結果列表 -->
		<!-- 每個搜尋結果顯示：標題、內容、私訊按鈕、收藏按鈕 -->
		<c:if test="${not empty jobCollectedList}">
			<c:forEach var="job" items="${jobCollectedList}">
				<div class="post">
					<div class="result">
						<span style="display: none;">${job.jID}</span>
						${job.jobName}<br>${job.jContent}
					</div>
					<button class="btn-delete" data-action="delete"
						data-jid="${job.jID}" onclick="deleteJob(this)">刪除</button>
					<button class="btn-message" data-action="message" data-cid="${job.cID}" onclick="message(this)">私訊</button>
				</div>
			</c:forEach>
		</c:if>

		<script>

			function deleteJob(button) {
				var action = button.getAttribute('data-action');
				var jID = button.getAttribute('data-jid');
				var postElement = button.parentNode;

				var xhttp = new XMLHttpRequest();

				xhttp.open("GET", "CollectServlet?action="
						+ encodeURIComponent(action) + "&jID="
						+ encodeURIComponent(jID), true);

				xhttp.onreadystatechange = function() {
					if (xhttp.readyState === 4 && xhttp.status === 200) {
						console.log("Job deleted successfully.");
						postElement.parentNode.removeChild(postElement);
					}
				};

				xhttp.send();
			}

			function message(button) {
				var cID = button.getAttribute('data-cid');
				window.location.href = "MessageServlet?cID=" + encodeURIComponent(cID);
			}
		</script>
	</div>
	</div>

</body>
</html>