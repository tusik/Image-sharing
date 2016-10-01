<%--
  Created by IntelliJ IDEA.
  User: zinc
  Date: 2016/9/30
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
          + request.getServerName() + ":" + request.getServerPort()
          + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <base href="<%=basePath%>">
  <title>上传文件</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <script type="text/javascript" src="check.js"></script>
</head>
<body>
<form action="<%=request.getContextPath()%>/Upload"
      method="post" enctype="multipart/form-data">
  选择文件：<input type="file" accept="image/*" id="file" name="filename" onchange="fileChange(this)"/> <input type="submit"
                                                    name="file_submit" value="提交" >
</form>
</body>
</html>