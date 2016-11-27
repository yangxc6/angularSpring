package com.xc.as.core.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yxc on 2016/11/26.
 */
public class JsoupCrawler {

    private int timeoutLim = 15000;

   public List<HashMap<String, Object>> crawl(String url, Map<String, Object> data, ICallback callback) throws IOException {
        Document doc = Jsoup.connect(url).timeout(timeoutLim).get();
        HashMap<String, Object> response = new HashMap<String, Object>();
        response.put("doc", doc);
        response.put("url", url);
        List<HashMap<String, Object>> caughtData = callback.callbackAction(response);
        return caughtData;
    }
}
