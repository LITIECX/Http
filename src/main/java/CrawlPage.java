import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import dataHandle.TableData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class CrawlPage {

    public static void main(String[] args) throws Exception {
        CrawlPage crawl = new CrawlPage();
        crawl.crawlPageWithoutAnalyseJs("http://score.xaau.edu.cn/eams/localLogin.action");

    }

    /**
     * 功能描述：抓取页面时不解析页面的js
     *
     * @param url
     * @throws Exception
     */
    public void crawlPageWithoutAnalyseJs(String url) throws Exception {
        try {
            WebClient webClient = new WebClient(BrowserVersion.CHROME);
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setRedirectEnabled(true);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setTimeout(5000);
            webClient.getCookieManager().setCookiesEnabled(true);
            HtmlPage page = webClient.getPage(url);
            HtmlForm form = page.getFormByName("loginForm");
            final HtmlSubmitInput button = form.getInputByName("submitBtn");
            HtmlTextInput username = (HtmlTextInput) form.getInputByName("username");
            HtmlPasswordInput password = (HtmlPasswordInput) form.getInputByName("password");
            username.setValueAttribute("1640402200836");
            password.setValueAttribute("1640402200836");
            HtmlPage retPage = (HtmlPage) button.click();
            webClient.waitForBackgroundJavaScript(5000);
//           System.out.println(retPage.asXml());
            HtmlPage page1 = webClient.getPage("http://score.xaau.edu.cn/eams/courseTableForStd.action");
            webClient.waitForBackgroundJavaScript(5000);
//            System.out.println(page1.asXml());
            HtmlPage page2 = webClient.getPage("http://score.xaau.edu.cn/eams/home.action");
            webClient.waitForBackgroundJavaScript(3000);
//            System.out.println(page2.asXml());
            webClient.close();
            System.out.println("lttttt*************************************************");
            String TableHtml = page1.asXml();
            String userHtml  = page2.asXml();
            dataHandle(TableHtml,userHtml);  //解析数据
        } catch (Exception e) {
            System.err.println("Exception: " + e);
        }
    }

    public void dataHandle(String table ,String user){
        List<TableData> list = new ArrayList<TableData>();
        Document document0 = Jsoup.parse(user);
        String userdata = document0.select("div[class=banner_area]").select("a[target=_blank]").text();
        System.out.println(userdata);
        Document document = Jsoup.parse(table);
        Elements element = document.select("table[id=manualArrangeCourseTable]").select("td[class=infoTitle]");
        System.out.println(element);
        for (Element ele : element) {
            String title = ele.select("td").text();  //.text()为解析标签中的文本内容
            String id = ele.attr("id");  //.text()为解析标签中的文本内容
            String rowspan = ele.attr("rowspan");  //.text()为解析标签中的文本内容
            System.out.println("课程的标题为:" + title+"课程id:"+id+"节数:"+rowspan );
//            list.add(new TableData(id,rowspan,title));
        }


    }



}



