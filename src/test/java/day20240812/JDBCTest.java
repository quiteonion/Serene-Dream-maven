package day20240812;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class JDBCTest {
    @Test
    public void testStatement() throws SQLException {
        //获取连接
        String url = "jdbc:mysql://localhost:3306/demo01";
        String user = "root";
        String password = "lcw0909.";
        Connection connection = DriverManager.getConnection(url, user, password);
        //定义对应的属性
        String query = "select id, name, balance from user";
        //将内部属性引出
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        //打印
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int balance = resultSet.getInt("balance");
            System.out.println(id + "-" + name + "-" + balance);
        }
    }
}
