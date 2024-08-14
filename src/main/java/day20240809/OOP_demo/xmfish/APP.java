package day20240809.OOP_demo.xmfish;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

public class APP {
    public static void main(String[] args) throws Exception {
        String d = GetFileUtil.getDownloader();
        String n = GetFileUtil.getNotificator();
        String u = GetFileUtil.getUrl();
        String f = GetFileUtil.getFindSomething();
        String r = GetFileUtil.getRepository();
        String p = GetFileUtil.getParser();
        GetFileUtil.disposition();

        String html = getHtml(u);
        List<URLContent> list = getContents(u, html);
        List<URLContent> ls = getUrlContents(u, list);
        extracted(u, ls);
    }
    private static Connection getConnection() throws SQLException {
        String url1 = "jdbc:mysql://localhost:3306/demo01";
        String user = "root";
        String password = "lcw0909.";
        Connection connection = DriverManager.getConnection(url1, user, password);
        return connection;
    }

    private static void extracted(String url, List<URLContent> ls) {
        //输出
        System.out.println("准备输出..." + url);
        Tificator tificator = new XMFishTificator();
        tificator.up(ls);
        System.out.println("结束输出..." + url);
    }

    private static List<URLContent> getUrlContents(String url, List<URLContent> list) throws SQLException {
        //存储
        System.out.println("准备存入..." + url);
        Ropository ropository = new XMFishRopository();
        List<URLContent> ls = ropository.getHtml(list);
        System.out.println("存入完成..." + url);
        return ls;
    }

    private static List<URLContent> getContents(String url, String html) {
        //解析
        System.out.println("准备解析..." + url);
        Parser parser = new XMFishParser();
        List<URLContent> list = parser.parser(html);
        System.out.println("解析完成..." + url);
        return list;
    }

    private static String getHtml(String url) {
        //下载
        System.out.println("准备下载..." + url);
        Downloader downloader = new XMFishDownloader();
        String html = downloader.downloader(url);
        System.out.println("下载完成..." + url);
        return html;
    }
}
