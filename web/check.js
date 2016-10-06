/**
 * Created by zinc on 2016/10/1.
 */
function fileChange(target) {
    var fileSize = document.getElementById("myFile").files[0].size;
    var size = fileSize / 1024;
    if(size>10000){
        alert("图片不能大于10M");
        target.value="";
        return
    }
    var name=target.value;
    var fileName = name.substring(name.lastIndexOf(".")+1).toLowerCase();
    if(fileName !="jpge" && fileName !="jpg"&& fileName !="png"&& fileName !="bmp"&& fileName !="ico"&& fileName !="gif"&& fileName !="jpeg"){
        alert("请选择图片文件上传！");
        target.value="";
        return
    }
}
document.getElementById("myFile").addEventListener("change", function(){

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
        }
        //以multipart/form-data方式编码
        var formData=new FormData();
        formData.append("myFile",file);
        xhr.send(formData);

});