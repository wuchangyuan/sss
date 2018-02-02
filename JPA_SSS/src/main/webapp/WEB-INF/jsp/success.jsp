<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
	$(function () {
		if ("${editMsg==null}"=="false") {
			alert("${editMsg }");
			location.href = "<%=request.getContextPath()%>/stu/list.html";
		}

		if ("${deleteMsg==null}"=="false") {
			alert("${deleteMsg }");
			location.href = "<%=request.getContextPath()%>/stu/list.html";
		}

		if ("${addClaMsg==null}"=="false") {
			alert("${addClaMsg }");
			location.href = "<%=request.getContextPath()%>/stu/list.html";
		}

		if ("${delRankMsg==null}"=="false") {
			alert("${delRankMsg }");
			location.href = "<%=request.getContextPath()%>/rank/rankJsp.html";
		}
	});
</script>
</head>
<body>
</body>
</html>