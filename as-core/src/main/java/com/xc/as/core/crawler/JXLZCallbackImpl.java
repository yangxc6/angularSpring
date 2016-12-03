package com.xc.as.core.crawler;

import com.xc.as.core.model.JsoupCrawler_JXLZ;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by yxc on 2016/11/26.
 */

public class JXLZCallbackImpl implements CrawlerInterface{
    private String baseurl = "http://www.jxlz.gov.cn/";

//    @Autowired
    protected MongoTemplate mongoOps;

	public JXLZCallbackImpl() throws Exception{
//        ApplicationContext ac = new AnnotationConfigApplicationContext(MongoConfig.class);
//        this.mongoOps = (MongoTemplate) ac.getBean("mongoTemplate");

		if(null == this.mongoOps){
			throw new Exception();
		}
	}

    public void on_start(String url){
        if(null == url){
            url = baseurl;
        }else{
            baseurl = url;
        }
        JsoupCrawler crl = new JsoupCrawler();

        try{
            crl.crawl(url, null, new ICallback(){

                public List<HashMap<String, Object>> callbackAction(HashMap<String, Object> response){
                    Document doc =  (Document) response.get("doc");

                    Elements href = doc.select("a.currentC");
                    for(Element e:href){
                        sub_page(e.attr("abs:href"));
                    }

                    href = doc.select("a.currentD");
                    for(Element e:href){
                        sub_page(e.attr("abs:href"));
                    }
                    return null;
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void sub_page(String url){
        JsoupCrawler crl = new JsoupCrawler();

        final Pattern pattern = Pattern.compile(".*htm$");

        try{
            crl.crawl(url, null, new ICallback(){

                public List<HashMap<String, Object>> callbackAction(HashMap<String, Object> response){
                    Document doc =  (Document) response.get("doc");
                    Elements href = doc.select("a[href]");
                    for(Element e:href){
                        String subUrl = e.attr("abs:href");
                        if(!pattern.matcher(subUrl).matches()){
                            page_item(subUrl);
                        }
                    }
                    return null;
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void page_item(String url){
        JsoupCrawler crl = new JsoupCrawler();
        try{
            //System.out.println(url);
            crl.crawl(url, null, new ICallback(){

                public List<HashMap<String, Object>> callbackAction(HashMap<String, Object> response){
                    Document doc =  (Document) response.get("doc");
                    Elements pages = doc.select("a.STYLE1");
                    for(Element p:pages){
                        page_item(p.attr("abs:href"));
                    }
                    Elements news = doc.select(".title > a");

                    for(Element n:news){
                        page_content(n.attr("abs:href"));
                    }
                    return null;
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void page_content(final String url){
        JsoupCrawler crl = new JsoupCrawler();
        try{
            System.out.println("JsoupCrawler is visiting: " + url);
            crl.crawl(url, null, new ICallback(){

                public List<HashMap<String, Object>> callbackAction(HashMap<String, Object> response){
                    Document doc =  (Document) response.get("doc");
                    String title = doc.select("td[style*=color: rgb(31, 87, 129)]").text();
                    String content = doc.select(".TRS_Editor").text();
                    if(0 < title.length() && 0 < content.length()){

                        Update update = new Update();
                        update.set("title", title)
                                .set("content", content)
                                .set("url", url)
                                .set("hashCode", (title+baseurl).hashCode())
                                .set("createdDate", new Date())
                                .set("sourceUrl", baseurl);

                        mongoOps.upsert(new Query(Criteria.where("hashCode").is((title+baseurl).hashCode())),
                                update, JsoupCrawler_JXLZ.class);
                    }
                    return null;
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run() throws Exception{
        JXLZCallbackImpl obj = new JXLZCallbackImpl();
        obj.on_start(null);
    }

    public void setMongoTemplate(MongoTemplate mongoOps) {
        this.mongoOps = mongoOps;
    }

}
