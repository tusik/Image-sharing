package cx.by.img;

/**
 * Created by zinc on 2016/10/2.
 */
import java.io.*;
import java.util.Properties;

public class ConfigLoader {

    String DIR="/usr/local/apache-tomcat-8.5.5/img/";
    public String getProjectDir(){
        return this.DIR;
    }
    public static String GetValueByKey( String key)  {
        ConfigLoader CL=new ConfigLoader();
        String filePath =CL.DIR+"/WEB-INF/.properties";
            Properties pps = new Properties();
            try {
                InputStream in = new BufferedInputStream(new FileInputStream(filePath));
                pps.load(in);
                String value = pps.getProperty(key);
                return value;
            }catch (IOException e) {
                e.printStackTrace();
                return null;
          }
    }
    /**Debug
    public static void main( String[] args)  {
       ConfigLoader CL=new ConfigLoader();
        System.out.println(CL.GetValueByKey("UPLOADDIR"));
    }*/
}
