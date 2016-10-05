/**
 * Created by zinc on 2016/10/1.
 */
function fileChange(target) {
    var fileSize = document.getElementById("file").files[0].size;
    var size = fileSize / 1024;
    if(size>10000){
        alert("图片不能大于10M");
        target.value="";
        return
    }
    var name=target.value;
    var fileName = name.substring(name.lastIndexOf(".")+1).toLowerCase();
    if(fileName !="jpge" && fileName !="jpg"&& fileName !="png"&& fileName !="bmp"&& fileName !="ico"&& fileName !="gif"){
        alert("请选择图片文件上传！");
        target.value="";
        return
    }
}