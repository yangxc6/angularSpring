var CrawlerService = function(RestService){
    var self = this;
    self.restService = RestService;

    self.saveCrawler = function(crawler, hour, successcb, errorcb){
        return self.restService._post('crawler/saveCrawler?crawler='+crawler+'&hour='+hour,null, successcb, errorcb, false);
    }

    self.getCrawlerClass = function(successcb, errorcb){
        return self.restService._get('crawler/getAllCrawlerClass', successcb, errorcb, false);
    }

    self.getCrawlerTable = function(successcb, errorcb){
        return self.restService._get('crawler/getCrawlerTable',successcb, errorcb, false);
    }

    self.deleteCrawler = function(crawler, hour, successcb, errorcb){
        return self.restService._delete('crawler/deleteCrawler?crawler='+crawler+'&time='+hour,successcb, errorcb);
    }

}