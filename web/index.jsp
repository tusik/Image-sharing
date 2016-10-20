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
    <link href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/index.css" rel="stylesheet">
</head>
<body>
<div class="container">
        <input type="file" accept="image/*" id="myFile" name="myFile" class="itxt "/>
    <progress  max="100" id="progress"  value="0"  style="display: none" ></progress>
      <%--
    if(session.getAttribute("url")!=null){
        out.print("<p><a href='"+session.getAttribute("url")+"'>"+request.getScheme() + "://"
          + request.getServerName() + path + "/"+session.getAttribute("url")+"</a>|"+Integer.parseInt(session.getAttribute("size").toString())/1024+"Kb</p>");
    }
  --%>
</div>
<div class="main" id="img">

</div>
<script type="text/javascript">

    document.getElementById("myFile").addEventListener("change", function(){
        var fileSize = this.files[0].size;
        var size = fileSize / 1024;
        if(size>10000){
            alert("图片不能大于10M");
            this.value="";
            return
        }
        var name=this.value;
        var fileName = name.substring(name.lastIndexOf(".")+1).toLowerCase();
        if(fileName !="jpg"&& fileName !="png"&& fileName !="bmp"&& fileName !="ico"&& fileName !="gif"&& fileName !="jpeg"){
            alert("请选择图片文件上传！");
            this.value="";
            return
        }else{
            //1、获取选择文件
            var file=this.files[0];
            if(!file)return;
            //2、新建文件
            var xhr=new XMLHttpRequest();
            xhr.open("POST", "Upload");
            //3.1、监听上传进度
            xhr.upload.onprogress=function(e){
                if(e.lengthComputable){
                    document.getElementById("progress").value=Math.floor(100*e.loaded/e.total);
                }
            }
            //3.2、开始时，重置起始值为0,同时显示
            xhr.onloadstart=function(){
                document.getElementById("progress").value="0";
                document.getElementById("progress").style.display="";
            }
            //3.3 上传完毕时，隐藏进度条
            xhr.onloadend=function(){
                document.getElementById("progress").style.display="none";
                //alert(xhr.responseText);
                var path=window.location.protocol+"//";
                if(window.location.port!=null)path+=":"+window.location.port+"/";
                path+=window.location.hostname+"/";
                var t = JSON.parse(xhr.responseText);
                var imgdiv = document.getElementById("img");
                var width=t.imgWidth;
                if(width>1080)width=1080;
                imgdiv.innerHTML="<p><a href='"+t.url+"'>"+window.location.hostname+"/"+t.url+"</a></p>"+
                        "<img src='"+t.url+"' width='"+width+"' height='auto'>";

                //location.reload();
            }
            //以multipart/form-data方式编码
            var formData=new FormData();
            formData.append("myFile",file);
            xhr.send(formData);

        }

    });

</script>
</body>
</html>