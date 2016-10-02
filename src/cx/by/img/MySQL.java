package cx.by.img;

import java.sql.*;

/**
 * Created by zinc on 2016/10/2.
 */
public class MySQL {

    public static final String url = "jdbc:mysql://127.0.0.1/images";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "123123";
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

    public boolean md5Check(String md5){
        ResultSet rs=null;
        sql="SELECT id FROM files where md5='"+md5+"'";
        MySQL db=new MySQL();
        db.MySql(sql);
        try {
            rs=db.pst.executeQuery();
            if (rs.next()){
                db.close();
                rs.close();
                return false;
            }else {
                db.close();
                rs.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
