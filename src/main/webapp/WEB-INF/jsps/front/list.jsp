<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!-- 引入jstl -->
<!-- 指令标签(jsp中的指令，不是html标签)不能用include动作标签引入，只能用指令引入 -->
<%@include file="/WEB-INF/jsps/common/tag.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title>秒杀列表页</title>
<%-- <%@include file="/WEB-INF/jsps/common/head.jsp"%> --%>
<jsp:include page="/WEB-INF/jsps/common/head.jsp"></jsp:include>
</head>
<body>
	<!-- 页面显示部分 -->
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading text-center">
				<h2>秒杀列表</h2>
			</div>
			<div class="panel-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>宝贝</th>
							<th>剩余数量</th>
							<th>秒杀开始时间</th>
							<th>秒杀结束时间</th>
							<th>活动创建时间</th>
							<th>秒杀详情页</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sk" items="${list}">
							<tr>
								<td>${sk.name}</td>
								<td>${sk.number}</td>
								<td><fmt:formatDate value="${sk.startTime}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td><fmt:formatDate value="${sk.endTime}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td><fmt:formatDate value="${sk.createTime}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td><a href="<%=basePath %>seckill/${sk.seckillId}/detail"
									target="_self" class="btn btn-info">查看详情</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>