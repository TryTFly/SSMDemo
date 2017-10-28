window.onload = function() {
	Highlight();
	// 鼠标移动改变背景,可以通过给每行绑定鼠标移上事件和鼠标移除事件来改变所在行背景色。
}
// 编写一个函数，供添加按钮调用，动态在表格的最后一行添加子节点；
function addOne(obj) {
	var tbody = document.getElementById('table').lastChild;
	var tr = document.createElement('tr');

	var td = document.createElement("td");
	td.innerHTML = "<input type='text' name='name[]'/>";
	tr.appendChild(td);

	td = document.createElement("td");
	td.innerHTML = "<input type='text' name='number[]'/>";
	tr.appendChild(td);

	td = document.createElement("td");
	td.innerHTML = "<input type='text' name='startTime[]'/>";
	tr.appendChild(td);

	td = document.createElement("td");
	td.innerHTML = "<input type='text' name='endTime[]'/>";
	tr.appendChild(td);
	
	td = document.createElement("td");
	td.innerHTML = "<a href='javascript:;' onclick='deleteRow(this)'>删除</a>";
	tr.appendChild(td);

	tbody.appendChild(tr);
	Highlight();
}
// 创建删除函数
function deleteRow(obj) {
	var tbody = document.getElementById('table').lastChild;
	var tr = obj.parentNode.parentNode;
	var p1=document.getElementById("test1");
	var p2=document.getElementById("test2");
	p1.innerHTML=tbody.nodeName;
	p2.innerHTML=tr.nodeName;
	tbody.removeChild(tr);
	
}
// 鼠标移动改变背景,可以通过给每行绑定鼠标移上事件和鼠标移除事件来改变所在行背景色。
function Highlight() {
	var tbody = document.getElementById('table').lastChild;
	trs = tbody.getElementsByTagName('tr');
	for (var i = 1; i < trs.length; i++) {
		trs[i].onmouseover = function() {
			this.style.backgroundColor = "pink";
		}
		trs[i].onmouseout = function() {
			this.style.backgroundColor = "#fff";
		}
	}
}