package day20240809.OOP_demo.xmfish;

import lombok.Getter;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * 工具类，对外展示配置的文件
 */

public class GetFileUtil {
    //获取所需要的内容
    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("src/main/java/day20240809/OOP_demo/xmfish/confile.properties"));
            downloader = (String) properties.get("downloader");
            parser = (String) properties.get("parser");
            repository = (String) properties.get("repository");
            notificator = (String) properties.get("notificator");
            url = (String) properties.get("url");
            findSomething = (String) properties.get("findSomething");
            name = (String) properties.get("name");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //配置对应的数据
    @Getter
    private static final String downloader;
    @Getter
    private static final String parser;
    @Getter  //parser: Jsoup,
    private static final String repository;
    @Getter
    private static final String notificator;
    @Getter//notificator: console, email,
    private static final String url;
    @Getter//各个网站
    private static final String findSomething;
    @Getter//查找的东西
    private static final String name;//查找的东西

    public static void disposition() throws SQLException {
        Connection connection = getConnection();
        update(connection);
    }


    private static void update(Connection connection) throws SQLException {
        String s = "insert into confile(downloader, parser, repository, notificator, url, findSomething, name) values (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(s);
        preparedStatement.setString(1, downloader);
        preparedStatement.setString(2, parser);
        preparedStatement.setString(3, repository);
        preparedStatement.setString(4, notificator);
        preparedStatement.setString(5, url);
        preparedStatement.setString(6, findSomething);
        preparedStatement.setString(7, name);
        preparedStatement.executeUpdate();
    }

    private static Connection getConnection() throws SQLException {
        String url1 = "jdbc:mysql://localhost:3306/demo01";
        String user = "root";
        String password = "lcw0909.";
        Connection connection = DriverManager.getConnection(url1, user, password);
        return connection;
    }
}