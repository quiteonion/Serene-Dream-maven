package day20240809.OOP_demo.xmfish;

import java.util.List;

public class XMFishTificator extends Tificator{
    @Override
    public void up(List<URLContent> list) {
        for (URLContent content : list) {
            System.out.println(content.toString());
        }
    }
}
