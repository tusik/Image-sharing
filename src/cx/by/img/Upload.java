package cx.by.img;

/**
 * Created by zinc on 2016/9/30.
 */

import org.apache.commons.lang3.RandomStringUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import org.json.JSONObject;
import org.json.JSONArray;

import static java.lang.System.out;

@MultipartConfig(location = "/usr/local/apache-tomcat-8.5.5/img/img/", maxFileSize = 1024 * 1024 * 10)

public class Upload extends HttpServlet{
    MD5 md5=new MD5();
    ConfigLoader CL=new ConfigLoader();
    MySQL md5check=new MySQL();
    RandomName rn=new RandomName();
    String DIR=CL.getProjectDir();
    String UPLOADDIR=CL.GetValueByKey("UPLOADDIR");
    private static final long serialVersionUID = 1L;

    public Upload() {
        super();
    }

    public void destroy() {
        super.destroy();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringBuffer url = request.getRequestURL();
        request.setCharacterEncoding("utf-8");
        Part part = request.getPart("myFile");//获取文件名称
        String filename = getFilename(part);
        filename = rn.nameCheck(filename);
        creatDir(DIR+UPLOADDIR+"/"+getDir());
        part.write(DIR+UPLOADDIR+"/"+getDir()+filename);
        String filemd5=md5.getMd5ByFile(new File(DIR+UPLOADDIR+"/"+getDir()+filename));

        if(md5check.md5Check(filemd5).equals("null")){
            md5check.insertInfo(request.getRemoteAddr(),getDir()+filename,filemd5);
            filename=getDir()+filename;
        }else{
            filename=md5check.md5Check(filemd5);
            //deleteFile(DIR+UPLOADDIR+"/"+getDir()+filename);
        }
        FileInputStream fis = new FileInputStream(new File(DIR+UPLOADDIR+"/"+filename));
        BufferedImage bufferedImg = ImageIO.read(fis);
        //HttpSession session=request.getSession();
        //session.setAttribute("url",UPLOADDIR+"/"+filename);
        //session.setAttribute("size",request.getContentLength());
        //session.setAttribute("imgWidth",bufferedImg.getWidth());
        //session.setAttribute("imgHeight",bufferedImg.getHeight());
        JSONObject JO1 = new JSONObject();
        JO1.put("url",UPLOADDIR+"/"+filename);
        JO1.put("size",request.getContentLength());
        JO1.put("imgWidth",bufferedImg.getWidth());
        JO1.put("imgHeight",bufferedImg.getHeight());
        response.getWriter().write(String.valueOf(JO1));
        //response.getWriter().println(DIR+UPLOADDIR+"/"+getDir()+filename);
        //response.getWriter().println(bufferedImg.getWidth());
        //response.sendRedirect("/");
    }
    private String getDir(){
        Calendar now = Calendar.getInstance();
        return Integer.toString(now.get(Calendar.YEAR))+(now.get(Calendar.MONTH)+1)+now.get(Calendar.DAY_OF_MONTH)+"/";
    }
    public void creatDir(String dir){
        File file =new File(dir);//创建文件夹
        if  (!file .exists()  && !file .isDirectory())
        {
            file .mkdir();
        } else {}
    }
    private String getRandomString(){
        return "sa";
    }

    private String getFilename(Part part) {
        if (part == null) {
            return null;
        }
        String fileName = part.getHeader("content-disposition");
        if (isBlank(fileName)) {
            return null;
        }
        return substringBetween(fileName, "filename=\"", "\"");
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0)
            return true;
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String substringBetween(String str, String open, String close) {
        if (str == null || open == null || close == null)
            return null;
        int start = str.indexOf(open);
        if (start != -1) {
            int end = str.indexOf(close, start + open.length());
            if (end != -1)
                return str.substring(start + open.length(), end);
        }
        return null;
    }

    public void deleteFile(String sPath) {
        File file= new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
        }
    }

    public void init() throws ServletException {

    }
}
