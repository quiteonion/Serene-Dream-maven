package day20240809.OOP_demo.xmfish;

import java.sql.*;
import java.util.List;

public class XMFishRopository extends Ropository {
    @Override
    public List<URLContent> getHtml(List<URLContent> list) throws SQLException {
        //连接数据库
        Connection connection = getConnection();
        //删除目前数据库内的所有的数据
        extracted(connection);
        for (int i = 0; i < list.size(); i++) {
            URLContent urlContent = list.get(i);
            XMFishAbout xmFishAbout = (XMFishAbout) urlContent;
            //获取集合中元素
            String title = xmFishAbout.getContent();
            String quality = xmFishAbout.getQuality();
            String url = xmFishAbout.getUrl();
            String time = xmFishAbout.getTime();


            String insertSql = "insert into article(title, quality, url, time) values(?, ?, ?, ?);";
            PreparedStatement pStmt = connection.prepareStatement(insertSql);
            pStmt.setString(1, title);
            pStmt.setString(2, quality);
            pStmt.setString(3, url);
            pStmt.setString(4, time);
            pStmt.executeUpdate();


            System.out.println("=== === ===");

        }
        return list;
    }

    private static void extracted(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("truncate table article");
        preparedStatement.executeUpdate();
    }

    private static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/demo01";
        String user = "root";
        String password = "lcw0909.";
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
}
