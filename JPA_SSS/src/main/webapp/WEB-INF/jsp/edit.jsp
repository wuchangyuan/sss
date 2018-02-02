<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath() %>/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
	function tj() {
		if ($("#name").val()=="") {
			alert("姓名不能为空");
		}else if(typeof($("input[name=sex]:checked").val())=="undefined"){
			alert("性别一定要选")
		}else if ($("#age").val()=="") {
			alert("年龄一定要填")
		}else if (isNaN($("#age").val())||$("#age").val()<=0) {
			alert("年龄必须是正整数")
		}else if ($("#cla").val()=="") {
			alert("班级一定要选")
		}else {
			$("form").submit();
		}
	}
	function cz() {
		$("form :input").not(":button, :submit, :reset, :hidden, :checkbox, :radio").val("");  
        $("form :input").removeAttr("checked").remove("selected"); 
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/stu/edit.html" method="post" style="align-self: center;">
		<input type="hidden" name="id" value="${student.id }">
		<table>
			<tr>
				<td>姓名：</td>
				<td>
					<input type="text" name="name" id="name" value="${student.name }">
				</td>
			</tr>
			<tr>
				<td>性别：</td>
				<td>
					<input type="radio" name="sex" value="男" ${student.sex=="男"?"checked=checked":"" }>男&nbsp;&nbsp;
					<input type="radio" name="sex" value="女" ${student.sex=="女"?"checked=checked":"" }>女
				</td>
			</tr>
			<tr>
				<td>年龄：</td>
				<td>
					<input type="text" name="age" id="age" value="${student.age }">
				</td>
			</tr>
			<tr>
				<td>班级：</td>
				<td>
					<select name="cla.id" id="cla">
						<option value="">请选择班级</option>
						<c:forEach items="${clas }" var="cla">
							<option value="${cla.id }" ${cla.id==student.cla.id?"selected=selected":"" }>${cla.name }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<input type="button" value="提交" onclick="tj()">
					<input type="button" value="重置" onclick="cz()">
					<input type="button" value="返回" onclick="window.history.go(-1)"> 
				</td>
			</tr>
		</table>
	</form>
</body>
</html>