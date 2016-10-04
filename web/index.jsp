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
    <script type="text/javascript" src="check.js"></script>
    <link href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/index.css" rel="stylesheet">

</head>
<body>
<div class="container">
  <form action="<%=request.getContextPath()%>/Upload"
        method="post" enctype="multipart/form-data">
        <input type="file" accept="image/*" id="file" name="filename" class="itxt " onchange="fileChange(this)"/>
        <input type="submit" name="file_submit" class="btn btn-default isubmit" value="提交" onclick="send()">
    <%
    if(session.getAttribute("url")!=null){
        out.print("<p><a href='"+session.getAttribute("url")+"'>"+basePath+session.getAttribute("url")+"</a></p>");
    }
  %>
  </form>
</div>
<div class="main">
    <%
        int imgWidth=0;
        if(session.getAttribute("url")!=null)
            imgWidth=Integer.parseInt(session.getAttribute("imgWidth").toString());
            if(imgWidth>1080){
                imgWidth=1080;
            }
            out.print("<img src='"+session.getAttribute("url")+"' width=\""+imgWidth+"px\" height=\"auto\">");
        session.setAttribute("url",null);
    %>
</div>

</body>
</html>