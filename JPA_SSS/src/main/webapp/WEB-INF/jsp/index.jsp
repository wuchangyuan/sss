<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath() %>/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
		function page(pageNum) {
            $("#pageNum").val(pageNum);
            $("#searchForm").submit();
        }
        function goPage() {
            var pageNum = $('#go').val()-1;
            if (isNaN(pageNum)) {
                alert("必须输入整数");
                return;
            }
            if (pageNum > parseInt("${stuPage.totalPages-1}")) {
                pageNum = parseInt("${stuPage.totalPages-1}");
            }
            if (pageNum < 0) {
                pageNum = 0;
            }
            page(pageNum)
        }
</script>
<title>Insert title here</title>
</head>
<body>

	<h1 align="center">学生列表</h1>
	<div style="text-align: center;">
		<form id="searchForm" action="<%=request.getContextPath()%>/stu/list.html" method="post">
			<input type="hidden" name="pageNum" id="pageNum"/>
			姓名：<input type="text" name="name" value="${student.name }">
			性别：<select name="sex">
				<option value="">请选择性别</option>
				<option value="男" ${student.sex=="男"?"selected=selected":"" }>男</option>
				<option value="女" ${student.sex=="女"?"selected=selected":"" }>女</option>
			</select>
			性别：<select name="cla.id">
				<option value="">请选择班级</option>
				<c:forEach items="${claList }" var="cla">
					<option value="${cla.id }" ${cla.id==student.cla.id?"selected=selected":"" }>${cla.name }</option>
				</c:forEach>
			</select>
			<input value="查询" type="submit">
		</form>
	</div>
	<table align="center" border="1px" cellspacing="0" width="800px">
		<tr>
			<td colspan="6">
				<a href="<%=request.getContextPath() %>/stu/get.html">添加学生</a>&nbsp;&nbsp;
				<a href="<%=request.getContextPath() %>/cla/addJsp.html">添加班级</a>&nbsp;&nbsp;
				<a href="<%=request.getContextPath()%>/rank/rankJsp.html">添加排名</a>
			</td>
		</tr>
		<tr>
			<th>序号</th>
			<th>姓名</th>
			<th>性别</th>
			<th>年龄</th>
			<th>班级</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${stuPage.content }" var="stu">
			<tr>
				<td>${stu.id }</td>
				<td>${stu.name }</td>
				<td>${stu.sex }</td>
				<td>${stu.age }</td>
				<td>${stu.cla.name }</td>
				<td>
					<a href="<%=request.getContextPath() %>/stu/get.html?id=${stu.id}">编辑</a>
					<a href="<%=request.getContextPath() %>/stu/delete.html?id=${stu.id}">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div style="text-align: center;">
		当前第${stuPage.number+1}页 共${stuPage.totalPages}页
        <!--如果是没有上一页，就不显示上一页的连接-->
        <c:if test="${stuPage.hasPrevious()}">
            <a href="#" onclick="page(${stuPage.number-1})">上一页</a>
        </c:if>
        <!--如果没有下一页，就不限时下一页连接-->
        <c:if test="${stuPage.hasNext()}">
            <a href="#" onclick="page(${stuPage.number+1})">下一页</a>
        </c:if>
        <input type="text" id="go" size="4" value="${param.pageNum+1}"><input type="button" value="跳转" onclick="goPage()"/>
	</div>
</body>
</html>