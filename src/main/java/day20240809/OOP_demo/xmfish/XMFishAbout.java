package day20240809.OOP_demo.xmfish;

import lombok.Getter;

public class XMFishAbout implements URLContent {

    private String quality;
    @Getter

    private String content;
    private String url;
    private String time;


    public XMFishAbout(String quality, String content, String url, String time) {
        this.quality = quality;
        this.content = content;
        this.url = url;
        this.time = time;
    }


    @Override
    public String toString() {
        return "性质  " + quality + '\n' +
                "标题  " + content + '\n' +
                "链接  " + url + '\n' +
                "发布时间  " + time + '\n';
    }
}
