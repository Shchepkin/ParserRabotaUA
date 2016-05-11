package ParserRabotaUA;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by InStaller on 11.05.2016.
 */
public class Parser {

    public static void main(String args[]) throws IOException {
//---------------------------------------------------------------------
        String keyWords = "QA";
//---------------------------------------------------------------------
        Document doc;
        int countVacancy = 0;
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String URL = "http://rabota.ua/jobsearch/vacancy_list?regionId=1&keyWords=";


// Connect and read a page
                doc = Jsoup.connect(URL + keyWords + "&period=2&lastdate=" + "10.05.2016" + "&pg=" + 1).get();


// Assert whether it is a last page
                if (doc.body().select(".rua-p-t_20").text().equals("Мы не нашли ничего, что точно соответствует вашему запросу!")) {
//                    System.out.println();
//                    System.out.println("Total number of pages: " + (i - 1));
//                    System.out.println("Total number of vacancies: " + countVacancy);
//                    break;
                    System.out.println("Мы не нашли ничего, что точно соответствует вашему запросу!");
                }
//                countVacancy += doc.body().select("a.t").size();
//                System.out.println(countVacancy);
                System.out.println(doc.body().select(".t").size());

        for (int j = 0; j < doc.body().select(".t").size(); j++) {
            System.out.println(doc.body().select(".t").get(j).text()); //name of vacancy
            System.out.println(doc.body().select(".s").get(j).text()); //name of company
            if(j<doc.body().select(".t").size()-1)System.out.println(doc.body().select(".dt").get(j).text()); //time of added
            System.out.println("http://rabota.ua" + doc.body().select(".t").get(j).attr("href")); //link
            System.out.println();
        }



    }
}