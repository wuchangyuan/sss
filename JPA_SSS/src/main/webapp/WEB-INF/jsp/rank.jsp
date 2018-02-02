<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath() %>/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
	$(function () {
		if ("${addRankMsg==null}"=="false") {
			alert("${addRankMsg }");
		}
	})
	function tj() {
		if ($("#no").val()==""||$("#name").val()==""||$("#score").val()=="") {
			alert("表单项不能为空");
		}else if (isNaN($("#no").val())||$("#no").val()<=0) {
			alert("组号必须是正整数")
		}else if (isNaN($("#score").val())||$("#score").val()<=0) {
			alert("分数必须是正整数")
		}else {
			$("form").submit();
		}
	}
	function getData() {
		if ($(".add").length==0) {
			$.ajax({
				url: "${pageContext.request.contextPath }/rank/list.html",
				data:{"pageNum":1,"pageSize":10},
				type:"get",
				success: function (data) {
					if (data.status) {
					    var pageList = data.data.content;
						var contentStr = "";
						for(var i=0;i<pageList.length;i++){
							var item = pageList[i];
							contentStr+="<tr class=\"add\"><td>"+item.id+"</td><td>"+item.score+"</td><td>"+item.groupNo+"</td><td>"+item.projectName+"</td><td><input class=\""+item.id+"\" type=\"button\" value=\"删除\" onclick=\"delRank(this)\"/>&nbsp;&nbsp;&nbsp;<input class=\""+item.id+"\" type=\"button\" value=\"修改\" onclick=\"modify(this)\"/>";
							}
						$("#tb").append(contentStr);
					} 
				}
			}); 
		}
	}
	function check(item) {
		var rid = $("#rid").val();
		$.ajax({
			url: "${pageContext.request.contextPath }/rank/check.html",
			data: "groupNo="+$(item).val()+"&id="+rid,
			type: "post",
			success: function (data) {
				if (!data.status) {
					$("#sp").html(data.msg);
					$("#btn").attr("disabled","disabled");
				}else {
					$("#sp").html("");
					$("#btn").removeAttr("disabled");
				}
			}
		})
	}

	function delRank(item) {
		var id = $(item).attr("class");
		location.href = "${pageContext.request.contextPath }/rank/delete.html?id="+id;
	}

	function modify(item) {
		var id = $(item).attr("class");
		$.ajax({
				url: "${pageContext.request.contextPath }/rank/get.html",
				data: {"id":id},
				type: "get",
				success: function (data) {
					if (data.status) {
						var rank = data.data;
						$("#no").val(rank.groupNo);
						$("#name").val(rank.projectName);
						$("#score").val(rank.score);
						$("#rid").val(rank.id);
					}
				}
			})
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<div style="text-align: center;">
	    <h1>新增排名信息</h1>
	   	<form action="<%=request.getContextPath() %>/rank/add.html" method="post">
	       <input value="${rank.id}" type="hidden" name="id" id="rid">
	       <input value="${rank.groupNo }" placeholder="请输入组号" name="groupNo" id="no" onblur="check(this)"><span id="sp" style="color: red;"></span><br/>
	       <input value="${rank.projectName }" placeholder="请输入项目名称" name="projectName" id="name"><br/>
	       <input value="${rank.score }" placeholder="请输入分数" name="score" id="score"><br/>
	       <input type="button" value="新增排名" onclick="tj()" id="btn">
	   	</form>
	</div>
	<div align="center">
		<input type="button" onclick="getData()" value="刷数据">
	</div>
	<table id="tb" border="1px" cellspacing="0" width="500px" align="center">
		<tr>
			<th>序号</th>
            <th>分数</th>
            <th>组号</th>
            <th>项目名称</th>
            <th>操作</th>
		</tr>
	</table>
</body>
</html>