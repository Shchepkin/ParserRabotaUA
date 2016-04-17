package ParserRabotaUA;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
/*
*
*   http://rabota.ua/jobsearch/vacancy_list?regionId=1&keyWords=&period=2&lastdate=15.04.2016&pg=2
*
*   "," = %2c
*
*   "_" = +
*
*   QA, Automated testing, Тестировщик игр, QA Engineer, QA automation engineer, Junior QA Engineer,
*   Software test engineer, Software tester, Game tester, Инженер по автоматизированому тестированию,
 *  Автоматизированное тестирование,
*
 */
public class App {

    public static void main(String args[]) {
//---------------------------------------------------------------------
        String keyWords = "QA%2c+" +
                "Automated+testing%2c+" +
                "Тестировщик+игр%2c+" +
                "QA+Engineer%2c+" +
                "QA+automation+engineer%2c+" +
                "Junior+QA+Engineer%2c+" +
                "Software+test+engineer%2c+" +
                "Software+tester%2c+" +
                "Game+tester%2c+" +
                "Инженер+по+автоматизированому+тестированию%2c+" +
                "Автоматизированное+тестирование";
//---------------------------------------------------------------------
        Document doc;
        int countVacancy = 0;
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String URL = "http://rabota.ua/jobsearch/vacancy_list?regionId=1&keyWords=";
        for (int i = 1; i == i; i++) {
            try {
// Connect and read a page
            doc = Jsoup.connect(URL + keyWords + "&period=2&lastdate=" + sdf.format(currentDate) + "&pg=" + i).get();
// Assert whether it is a last page
                if (doc.body().getElementsByClass("rua-p-t_20").text().equals("Мы не нашли ничего, что точно соответствует вашему запросу!")) {
                    System.out.println();
                    System.out.println("Total number of pages: " + (i - 1));
                    System.out.println("Total number of vacancies: " + countVacancy);
                    break;
                }

                countVacancy += doc.body().getElementsByClass("t").size();
// Print out the parsed info
                for (int j = 0; j < doc.body().getElementsByClass("t").size(); j++) {
                    System.out.println(doc.body().getElementsByClass("t").get(j).text()); //name of vacancy
                    System.out.println(doc.body().getElementsByClass("s").get(j).text()); //name of company
                    System.out.println("http://rabota.ua" + doc.body().getElementsByClass("t").get(j).attr("href")); //link
                    System.out.println();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}