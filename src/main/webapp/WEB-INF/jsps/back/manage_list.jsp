<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	out.print(path + "<br/>");
	out.print(basePath + "<br/>");
%>

<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
<title>内容列表页面</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/common/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/back/list.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/js/back/update.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/all.css"
	rel="stylesheet" type="text/css" />
<%-- <jsp:include page="/WEB-INF/jsps/common/head.jsp"></jsp:include> --%>

</head>
<body style="background: #e1e9eb;">
	<form action="<%=basePath%>manage/mlist" id="mainForm" method="post">
		<input type="hidden" name="currentPage" id="currentPage"
			value="${page.currentPage}" />
		<div class="right">
			<div class="current">
				当前位置：<a href="javascript:void(0)" style="color: #6E6E6E;">商品管理</a>
				&gt; 商品列表
			</div>
			<div class="rightCont">
				<p class="g_title fix">
					商品列表 <a class="btn03" href="<%=basePath%>manage/insertJsp">新 增</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
						class="btn03" href="javascript:deleteBatch('<%=basePath%>')">删
						除</a>
				</p>
				<table class="tab1">
					<tbody>
						<tr>
							<td width="90" align="right">商品名称：</td>
							<td><input name="name" type="text" class="allInput"
								value="${name}" /></td>
							<td width="85" align="right"><input type="submit"
								class="tabSub" value="查 询" /></td>
						</tr>
					</tbody>
				</table>
				<div class="zixun fix">
					<table id="tb2" class="tab2" width="100%">
						<tbody>
							<tr>
								<th><input type="checkbox" id="all"
									onclick="allChose(this)" value="全选" /></th>
								<th>序号</th>
								<th>商品名称</th>
								<th>库存</th>
								<th>秒杀开始时间</th>
								<th>秒杀结束时间</th>
								<th>活动创建时间</th>
								<th>操作</th>
							</tr>
							<c:forEach items="${mlist}" var="seckill" varStatus="status">

								<tr
									<c:if test="${status.index%2!=0}">style="background-color: #ECF6EE;"</c:if>>
									<td><input type="checkbox" name="id[]"
										value="${seckill.seckillId}" /></td>
									<td>${status.index+1}</td>
									<td>${seckill.name}</td>
									<td>${seckill.number}</td>
									<td>${seckill.startTime}</td>
									<td>${seckill.endTime}</td>
									<td>${seckill.createTime}</td>
									<td><a
										href="javascript:update('${seckill.seckillId}','<%=basePath%>')">修改</a>&nbsp;&nbsp;&nbsp;
										<a
										href="<%=basePath %>manage/deleteOne?id=${seckill.seckillId}&currentPage=${page.currentPage}"
										onclick="">删除</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class='page fix'>
						共 <b>${page.totalNumber}</b> 条
						<c:if test="${page.currentPage != 1}">
							<a href="javascript:changeCurrentPage('1')" class='first'>首页</a>
							<a href="javascript:changeCurrentPage('${page.currentPage-1}')"
								class='pre'>上一页</a>
						</c:if>
						当前第<span>${page.currentPage}/${page.totalPage}</span>页
						<c:if test="${page.currentPage != page.totalPage}">
							<a href="javascript:changeCurrentPage('${page.currentPage+1}')"
								class='next'>下一页</a>
							<a href="javascript:changeCurrentPage('${page.totalPage}')"
								class='last'>末页</a>
						</c:if>
						跳至&nbsp;<input id="currentPageText" type='text'
							value='${page.currentPage}' class='allInput w28' />&nbsp;页&nbsp;
						<a
							href="javascript:changeCurrentPage($('#currentPageText').val())"
							class='go'>GO</a>
					</div>
				</div>
				<div id="update"></div>
			</div>
		</div>
	</form>
</body>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>