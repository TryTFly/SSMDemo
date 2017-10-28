/**
 * 修改秒杀商品开始和结束时间
 */
var manage = {
	url : function(seckillId, startTime, endTime) {
		return "/SSMDemo/manage/" + seckillId + "/" + startTime + "/" + endTime
				+ "/updateTime";
	},
}

function doUpdate(seckillId) {
	var startTime = $('#sTime').val();
	var endTime = $('#eTime').val();
	if (startTime == null || startTime == '') {
		if (endTime == null || endTime == '') {
			startTime = 0;
			endTime = 0;
		}
	}
	$.post(manage.url(seckillId, startTime, endTime), {}, function(result) {
		var number = result;
		if (number === 0) {
			alert('日期格式错误,更新失败');
		} else {
			var flag = confirm('更新成功');
			if (flag) {
				$("#mainForm").submit();
			} else {
				update();
			}
		}
	});
}
function update(seckillId, basePath) {
	$('#update').css('margin', '0,auto');
	$("#update")
			.hide()
			.html(
					"开始时间:<input type='text' id='sTime' placeholder='格式:2010-11-11 00:00:00' name='startTime'/><br/>"
							+ "结束时间:<input type='text' id='eTime' placeholder='格式:2010-10-9 00:00:00' name='endTime'/><br/>"
							+ "<button type='button' onclick='doUpdate("
							+ seckillId + ")'>提交</button>").show();
}