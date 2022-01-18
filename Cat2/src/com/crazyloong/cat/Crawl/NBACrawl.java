package com.crazyloong.cat.Crawl;

import com.crazyloong.cat.pojo.NBATeam;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class NBACrawl {

    public List<NBATeam> getNBATeam () throws Exception{
        List<NBATeam> list = new ArrayList<>();
        Document doc =Jsoup.connect("https://china.nba.com/standings/").get();

        String location = "";
        Elements es = doc.getElementsByClass("nba-stat-table");
        Elements ess =doc.getElementsByTag("table");
        System.out.println(ess.size());
        for (Element e : ess) {
            location = e.toString();
            System.out.println(location);
        }

        return null;
    }

    public static void main(String[] args) throws Exception{
        NBACrawl nba = new NBACrawl();
        nba.getNBATeam();
    }
}
