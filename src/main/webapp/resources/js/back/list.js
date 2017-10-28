/**
 * (通过调用Servlet)调用后台批量删除方法
 */
function deleteBatch(basePath) {
	$("#mainForm").attr("action", basePath + "manage/deleteBatch")
	$("#mainForm").submit();
}

/**
 * 全选
 */
function allChose(cbobj) {
	var tags = document.getElementsByTagName("input");
	if (cbobj.checked) {
		for (i = 0; i < tags.length; i++) {
			if (tags[i].type == "checkbox" && tags[i].name == "id") {
				tags[i].checked = "true";
			}
		}
	} else {
		for (i = 0; i < tags.length; i++) {
			if (tags[i].type == "checkbox" && tags[i].name == "id") {
				tags[i].checked = false;
			}
		}
	}
}

/**
 * 修改当前页码，调用后台重新查询
 */
function changeCurrentPage(currentPage) {
	$("#currentPage").val(currentPage);
	$("#mainForm").submit();
}

//删除成功后提示
document.write("dasdasdadsadasdasdsadsada");