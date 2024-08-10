package day20240809.OOP_demo.xmfish;

import org.jsoup.Jsoup;

import java.io.IOException;

public class XMFishDownloader extends Downloader{

    @Override
    public String downloader(String url) {
        String html = "网页内容源码";
        /**
         * 对网站的源码下载下来
         */
        try {
            html = Jsoup.connect(url).get().html();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return html;
    }
}
