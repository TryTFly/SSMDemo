<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<title>秒杀的详情页</title>
<%@include file="/WEB-INF/jsps/common/head.jsp"%>
</head>
<body>
	<div class="container">
	<input type="hidden" id="basePath" value="<%=basePath %>">
		<div class="panel panel-default text-center">
			<div class="panel-heading">
				<h1>${seckill.name}</h1>
			</div>
			<div class="panel-body">
				<h2 class="text-danger">
					<!-- 显示time图标 -->
					<span class="glyphicon glyphicon-time"></span>
					<!-- 显示倒计时 -->
					<span class="glyphicon" id="seckill-box"></span>
				</h2>
			</div>
		</div>
	</div>

	<!-- 登录弹出层，输入电话 -->
	<div id="killPhoneModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title text-center">
						<span class="glyphicon glyphicon-phone"></span>秒杀电话:
					</h3>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-8 col-xs-offset-2">
							<input type="text" name="killPhone" id="killPhoneKey"
								placeholder="填手机号(*^_^*)" class="form-control" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<!-- 验证消息 -->
					<span id="killPhoneMessage" class="glyphicon"></span>
					<button type="button" id="killPhoneBtn" class="btn btn-success">
						<span class="glyphicon glyphicon-phone"></span> 提交
					</button>
				</div>
			</div>
		</div>
	</div>
</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>

<!--使用cdn 获取公共js http//www.bootcdn.cn  -->
<!-- jQuery cookie操作插件 -->
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>

<!-- jQuery countDown倒计时插件 -->
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.min.js"></script>

<!-- 编写交互逻辑 -->
<script type="text/javascript"
	src="<%=basePath%>/resources/js/front/seckill.js"></script>

<script type="text/javascript">
//这行，这里没问题
$(function(){
	//使用EL表达式传入参数
	seckill.detail.init({
		seckillId:${seckill.seckillId},
		startTime:${seckill.startTime.time},//转成毫秒
		endTime:${seckill.endTime.time}
		});
});
</script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>