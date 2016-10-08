package cx.by.img;

import java.sql.*;

/**
 * Created by zinc on 2016/10/2.
 */
public class MySQL {
    ConfigLoader CL = new ConfigLoader();
    public final String url = "jdbc:mysql://localhost/"+CL.GetValueByKey("DBNAME");
    public final String name = "com.mysql.jdbc.Driver";
    public final String user = CL.GetValueByKey("DBUSER");
    public final String password = CL.GetValueByKey("DBPASSWD");
    public Connection conn = null;
    public PreparedStatement pst = null;
    String sql=null;

    public MySQL(){super();}

    public void insertInfo(String ip,String filename,String md5){
        ResultSet rs=null;
        sql="INSERT INTO `files` (ip,filename,md5) VALUES('"+ip+"','"+filename+"','"+md5+"')";
        MySQL db=new MySQL();
        db.MySql(sql);
        try {
            db.pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void MySql(String sql) {
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
            pst = conn.prepareStatement(sql);//准备执行语句
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.conn.close();
            this.pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String md5Check(String md5){
        ResultSet rs=null;
        sql="SELECT filename FROM files where md5='"+md5+"'";
        MySQL db=new MySQL();
        db.MySql(sql);
        try {
            rs=db.pst.executeQuery();
            if (rs.next()){
                String s=rs.getString("filename");
                return s;
            }else {
                return "null";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}
