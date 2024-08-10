package day20240809.OOP_demo.xmfish;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class XMFishParser extends Parser {
    static List<URLContent> arr = new ArrayList<>();
    @Override
    public List<URLContent> parser(String html) {
        /**
         * 解析部分
         */
        Document document = Jsoup.parse(html);
        Elements es = document.select("tbody[id=threadlist]").select("tr[class=tr3]");


        // 循环遍历
        for (Element e : es) {
            // 过滤公告、置顶
            Element noticeAndTop = e.select("td.icon a i").first();
            if (noticeAndTop.hasAttr("alt") && "置顶帖标志".equals(noticeAndTop.attr("alt"))) {
                continue;
            }
//https://gitee.com/i-really-like-quietness/quite.git
            // 解析数据
            Element titleElement = e.select("td.subject a.subject_t").first();
            Elements quality = e.getElementsByClass("view").select("a");
            String tr = quality.text();
            String title = titleElement.text();
            String url = titleElement.attr("href");
            url = "http://bbs.xmfish.com/" + url;
            String createdAt = e.select("td.author").first().select("p").text();


            XMFishAbout xmFishAbout = new XMFishAbout(tr, title, url, createdAt);
            arr.add(xmFishAbout);
        }
        return arr;
    }
}
