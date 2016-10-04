/**
 * Created by zinc on 2016/10/2.
 */
function send(){

        xmlhttp.onreadystatechange = callback;//状态改变的时候执行这个函数,用来判断是否请求完毕
        xmlhttp.send();//请求服务器

}
function callback(){
//请求完成表示
    if(xmlhttp.readyState ==4 && xmlhttp.status==200){
        //alert(xmlhttp.responseText);//相应返回的text
// alert(xmlhttp.responseXML);//相应返回的xml
        if (xmlhttp.responseText!=null){//这里直接判断不为空,应该根据数据库返回值来进行不同的显示
            var spanid = document.getElementById("url");
            var url="http://"+document.domain+"/u"+xmlhttp.responseText;
            spanid.innerHTML ='<a href=\"'+url+'\">'+url+'</a>';
        }
    }
    return false;
}
function  sub() {
    return false;
}