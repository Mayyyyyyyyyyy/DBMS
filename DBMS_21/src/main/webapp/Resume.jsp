<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>個人履歷</title>
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

.container {
	max-width: 600px;
	margin: 0 auto;
	padding: 20px;
}

.container2 {
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
	margin-left: auto;
	margin-right: auto;
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
			<a class="logo" href="#"> <img src="1200x630wa.png" alt="">
			</a>
			<nav>
				<a href="RecommendServlet">工作推薦</a>
				<a href="SearchServlet">職缺搜尋</a>
				<a href="CollectServlet">我的收藏</a>
        		<a href="LoginServlet">登出</a>
			</nav>

		</div>
	</header>

	<h1>個人履歷</h1>
	<div class='container'>

		<h2>基本資訊</h2>
		<!-- 這邊會將註冊時填入的資料從資料庫當中找出來放在這邊 -->
		<form action="${requestUri}" method="post" accept-charset="UTF-8">
			<label for="userName">姓名:</label> <input type="text" name="userName"
				value="${userName}" /><br /> <label for="email">Email:</label> <input
				type="text" name="email" value="${email}" /><br /> <label
				for="gender">性別:</label> <input type="text" name="gender"
				value="${gender}" /><br /> <label for="age">年齡:</label> <input
				type="text" name="age" value="${age}" /><br /> <label for="level">年級:</label>
			<input type="text" name="level" value="${level}" /><br /> <label
				for="department">科系:</label> <input type="text" name="department"
				value="${department}" /><br /> <input type="submit" value="Save" />
		</form>

		<h2>專長一覽</h2>
		<div class="container2">
			<c:if test="${not empty skillList}">
				<c:forEach var="skill" items="${skillList}">
					<div class="post">
						<div class="result">
							<span style="display: none;">${skill.mID}</span>
							${skill.skillName}
						</div>
						<button class="btn-deleteSkill" data-action2="deleteSkill"
							data-skillid="${skill.mID}" onclick="deleteSkill(this)">刪除</button>
					</div>
				</c:forEach>
			</c:if>
			<a href="AddSkillServlet">新增專長</a>
		</div>

		<h2>履歷一覽</h2>
		<div class="container2">
			<c:if test="${not empty resumeList}">
				<c:forEach var="resume" items="${resumeList}">
					<div class="post">
						<div class="result">
							<span style="display: none;">${resume.resumeID}</span>
							${resume.resumeName}<br>${resume.rContent}
						</div>
						<button class="btn-delete" data-action="delete"
							data-resumeid="${resume.resumeID}" onclick="deleteResume(this)">刪除</button>
					</div>
				</c:forEach>
			</c:if>
			<a href="AddResumeServlet">新增履歷</a>
		</div>


		<script>
				function deleteResume(button) {
					var action = button.getAttribute('data-action');
					var resumeID = button.getAttribute('data-resumeid');
					var postElement = button.parentNode;

					var xhttp = new XMLHttpRequest();

					xhttp.open("GET", "ResumeServlet?action="
							+ encodeURIComponent(action) + "&resumeID="
							+ encodeURIComponent(resumeID), true);

					xhttp.onreadystatechange = function() {
						if (xhttp.readyState === 4 && xhttp.status === 200) {
							console.log("Resume deleted successfully.");
							postElement.parentNode.removeChild(postElement);
						}
					};

					xhttp.send();
				}
				
				function deleteSkill(button) {
					var action2 = button.getAttribute('data-action2');
					var mID = button.getAttribute('data-skillid');
					var postElement = button.parentNode;

					var xhttp = new XMLHttpRequest();

					xhttp.open("GET", "ResumeServlet?action2="
							+ encodeURIComponent(action2) + "&mID="
							+ encodeURIComponent(mID), true);

					xhttp.onreadystatechange = function() {
						if (xhttp.readyState === 4 && xhttp.status === 200) {
							console.log("Skill deleted successfully.");
							postElement.parentNode.removeChild(postElement);
						}
					};

					xhttp.send();
				}
			</script>



	</div>

	<footer>
		<p>&copy; 2023 Your Name</p>
	</footer>
</body>
</html>