package day20240809.OOP_demo.xmfish;

import java.sql.SQLException;
import java.util.List;

public abstract class Ropository {
    public abstract List<URLContent> getHtml(List<URLContent> list) throws SQLException;
}
