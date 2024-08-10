package day20240809.OOP_demo.xmfish;
import java.sql.*;
import java.util.List;

public class XMFishRopository extends Ropository {
    @Override
    public List<URLContent> getHtml(List<URLContent> list) throws SQLException {
        for (int i = 0; i < list.size(); i++) {
            URLContent urlContent = list.get(i);
            XMFishAbout urlContent1 = (XMFishAbout) urlContent;
            String s = urlContent1.getContent();
            String url = "jdbc:mysql://localhost:3306/demo01";
            String user = "root";
            String password = "lcw0909.";
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");

            // 插入
            insert(connection, s);

            System.out.println("=== === ===");

            // 插入之后再次查询
            select(connection);
        }
        return null;
    }
    private static void select(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        String selectSql = "select id, name, balance from user;";
        ResultSet rs = stmt.executeQuery(selectSql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int balance = rs.getInt("balance");
            System.out.println(id + "\t" + name + "\t" + balance);
        }
    }

    private static void insert(Connection connection, String s) throws SQLException {
        String insertSql = "insert into user(name, balance) values(?, ?);";
        PreparedStatement pStmt = connection.prepareStatement(insertSql);
        pStmt.setString(1, s);
        pStmt.setInt(2, 90);
        int i = pStmt.executeUpdate();
        System.out.println(i>0 ? "插入成功" : "未知情况");
    }
}
