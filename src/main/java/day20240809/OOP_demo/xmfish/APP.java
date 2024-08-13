package day20240809.OOP_demo.xmfish;

import java.io.FileReader;
import java.util.List;
import java.util.Properties;

public class APP {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileReader("src/main/java/day20240809/OOP_demo/xmfish/confile.properties"));
        String url = properties.getProperty("url");
        //下载
        System.out.println("准备下载..." + url);
        Downloader downloader = new XMFishDownloader();
        String html = downloader.downloader(url);
        System.out.println("下载完成..." + url);
        //解析
        System.out.println("准备解析..." + url);
        Parser parser = new XMFishParser();
        List<URLContent> list = parser.parser(html);
        System.out.println("解析完成..." + url);
        //存储
        System.out.println("准备存入..." + url);
        Ropository ropository = new XMFishRopository();
        List<URLContent> ls = ropository.getHtml(list);
        System.out.println("存入完成..." + url);
        //输出
        System.out.println("准备输出..." + url);
        Tificator tificator = new XMFishTificator();
        tificator.up(ls);
        System.out.println("结束输出..." + url);
    }
}
