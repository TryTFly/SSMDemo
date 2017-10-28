<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>InsertBatch</title>
<jsp:include page="/WEB-INF/jsps/common/head.jsp"></jsp:include>
<link type="text/css" href="<%=basePath %>resources/css/insert.css" rel="stylesheet"/>
</head>
<body>
	<div class="container">
		<div class="panel panel-default">
			<div>
				<h1>批量插入数据:</h1>
			</div>
			<div class="panel-body">
				<form action="<%=basePath%>manage/insertBatch" method="post">
					<table class="table table-hover" id="table">
						<tr>
							<th>秒杀商品名称</th>
							<th>库存</th>
							<th>秒杀开始时间</th>
							<th>秒杀结束时间</th>
						</tr>

						<tr>
							<td><input type="text" name="name[]" value=""></td>
							<td><input type="text" name="number[]" value=""></td>
							<td><input type="text" name="startTime[]"
								placeholder="格式:2010-10-9 00:00:00" value=""></td>
							<td><input type="text" name="endTime[]"
								placeholder="格式:2010-10-9 00:00:00" value=""></td>
							<td><a href="javascript:;" onclick="deleteRow(this)">删除</a></td>
							<!--在删除按钮上添加点击事件  -->
						</tr>
						<tr>
							<td><input type="text" name="name[]" value=""></td>
							<td><input type="text" name="number[]" value=""></td>
							<td><input type="text" name="startTime[]" value=""></td>
							<td><input type="text" name="endTime[]" value=""></td>
							<td><a href="javascript:;" onclick="deleteRow(this)">删除</a></td>
							<!--在删除按钮上添加点击事件  -->
						</tr>
					</table>
					<hr>
					<p hidden="hidden" id="test1"></p>
					<p hidden="hidden" id="test2"></p>
					<div>
						<div id="add">
							<!--在添加按钮上添加点击事件  -->
							<input type="button" value="添加一行" onclick="addOne()" name="add" />
						</div>
						<div id="doInsert">
							<input type="submit" value="添加数据" name="submit" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="<%=basePath%>resources/js/back/insert.js"></script>
</html>