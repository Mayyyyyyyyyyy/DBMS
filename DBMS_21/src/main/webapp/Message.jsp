<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<title>私訊</title>

<style>
</style>
<script>
</script>
</head>
<body>
	<section class="msger">
		<header class="msger-header">
			<div class="msger-header-title">
				<i class="fas fa-comment-alt"></i> 私訊
				<nav>
				<a href="RecommendServlet">工作推薦</a> <a href="SearchServlet">職缺搜尋</a> <a href="CollectServlet">我的收藏</a> <a href="ResumeServlet">履歷管理</a>
			</nav>
			</div>
			<div class="msger-header-options">
				<span><i class="fas fa-cog"></i></span>
			</div>
		</header>
		<!--聊天 block-->
		<div>
			<iframe src="ChatServlet" width="100%" height="90%"> </iframe>
		</div>
		<!--聊天 block-->

		<div>
			<iframe src="InputServlet" width="100%" height="10%"> </iframe>
		</div>
	</section>
	<script>
	</script>
</body>
</html>