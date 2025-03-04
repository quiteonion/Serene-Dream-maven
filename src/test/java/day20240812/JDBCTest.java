package day20240812;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class JDBCTest {
    static Connection connection;

    @Test
    public void testStatement() throws SQLException {


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

    @Test
    public void testPreparedStatement() throws SQLException {

        String query = "select id, name, balance from user where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, 1);
        ResultSet resultSet = preparedStatement.executeQuery();
        //打印
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int balance = resultSet.getInt("balance");
            System.out.println(id + "-" + name + "-" + balance);
        }
    }

    @Test
    public void insert() throws SQLException {
        String insert = "insert into user(name,balance) values(?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setString(1, "挽梦");
        preparedStatement.setInt(2, 100);
        preparedStatement.executeUpdate();

    }

    @Test
    public void batchInsert() throws SQLException {
        String insertSql = "insert into user(name, balance) values(?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
        preparedStatement.setString(1, "alex");
        preparedStatement.setInt(2, 50);
        preparedStatement.addBatch();

        preparedStatement.setString(1, "bob");
        preparedStatement.setInt(2, 30);
        preparedStatement.addBatch();

        preparedStatement.executeBatch();
    }

    @Test
    public void update() throws SQLException {
        String updateSql = "update user set balance = balance + 5 where name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
        preparedStatement.setString(1, "阿呆");
        preparedStatement.executeUpdate();
    }

    @Test
    public void delete() throws SQLException {
        String updateSql = "delete from user where name = ?";
        PreparedStatement pStmt = connection.prepareStatement(updateSql);
        pStmt.setString(1, "阿呆");
        pStmt.executeUpdate();

    }



    @Test
    public void testWithoutTransactions() {
        PreparedStatement ppstmt1 = null;
        PreparedStatement ppstmt2 = null;
        String update1 = "update user set balance = balance - 10 where id = ?";
        String update2 = "update user set balance = balance + 10 where id = ?";

        try {
            ppstmt1 = connection.prepareStatement(update1);
            ppstmt1.setInt(1, 4);
            ppstmt1.executeUpdate();

            int i = 2 / 0;

            ppstmt2 = connection.prepareStatement(update2);
            ppstmt2.setInt(1, 6);
            ppstmt2.executeUpdate();
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
        } finally {
            try {
                if (ppstmt1 != null) {
                    ppstmt1.close();
                }
                if (ppstmt2 != null) {
                    ppstmt2.close();
                }
            } catch (SQLException e) {
                System.out.println("finally 中又遇到了 SQLException: " + e.getMessage());
            }
        }
    }




    @Test
    public void testWithTransactions() {
        PreparedStatement ppstmt1 = null;
        PreparedStatement ppstmt2 = null;
        String update1 = "update user set balance = balance - 10 where id = ?";
        String update2 = "update user set balance = balance + 10 where id = ?";

        try {
            connection.setAutoCommit(false);

            ppstmt1 = connection.prepareStatement(update1);
            ppstmt1.setInt(1, 1);
            ppstmt1.executeUpdate();

            int i = 2 / 0;

            ppstmt2 = connection.prepareStatement(update2);
            ppstmt2.setInt(1, 2);
            ppstmt2.executeUpdate();

            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("rollback SQLException: " + e.getMessage());
            }
            System.out.println("SQLException: " + e.getMessage());
        } finally {
            try {
                if (ppstmt1 != null) {
                    ppstmt1.close();
                }
                if (ppstmt2 != null) {
                    ppstmt2.close();
                }
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("finally 中又遇到了 SQLException: " + e.getMessage());
            }
        }
    }

    @BeforeAll

    public static void getConnection() throws SQLException {
        //获取连接
        String url = "jdbc:mysql://localhost:3306/demo01";
        String user = "root";
        String password = "lcw0909.";
        connection = DriverManager.getConnection(url, user, password);
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }
}
