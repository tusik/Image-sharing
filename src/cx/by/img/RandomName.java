package cx.by.img;

import org.apache.commons.lang3.RandomStringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zinc on 2016/10/2.
 */
public class RandomName {
    MySQL check=new MySQL();
    public String random(String fileformat){
        String filename= RandomStringUtils.randomAlphanumeric(6).toString()+"."+fileformat;
        return filename;
    }
    public String nameCheck(String filename){
        String fn[] = filename.split("\\.");
        String fileformat = fn[fn.length-1];
        filename=random(fileformat);
        String sql="SELECT id FROM files where='"+filename+"'";
        check.MySql(sql);
        try {
            ResultSet rs=check.pst.executeQuery();
            while(rs.next()) {
                filename = random(fileformat);
                sql = "SELECT id FROM files where='" + filename + "'";
                check.MySql(sql);
                rs = check.pst.executeQuery();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            check.close();
        }
        return filename;
    }
}
