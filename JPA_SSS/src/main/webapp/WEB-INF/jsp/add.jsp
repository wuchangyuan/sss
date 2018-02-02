<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath() %>/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
	function tj() {
		if ($("#name").val()=="") {
			alert("班级名字不能为空")
		}else {
			$("form").submit();
		}
	}

	function check(item) {
		var name = $(item).val();
		$.ajax({
				url: "<%=request.getContextPath() %>/cla/check.html",
				data: {"name":name},
				type:"post",
				success: function (data) {
					if (!data.status) {
						$("#sp").html(data.message);
						$("#btn").attr("disabled","disabled");
					}else {
						$("#sp").html("");
						$("#btn").removeAttr("disabled");
					}
				}
			});
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath() %>/cla/add.html" method="post">
		班级名称：<input id="name" type="text" name="name" onblur="check(this)"><span style="color: red" id="sp"></span><br>
		<input id="btn" type="button" value="提交" onclick="tj()">
		<input type="button" value="返回" onclick="window.history.go(-1)">	
	</form>
</body>
</html>