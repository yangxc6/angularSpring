/**
 * Created by yxc on 2016/12/5.
 */

var CrawlerController;
CrawlerController = function (StrToNumFilterFilter, CrawlerService) {
    var self = this;
    self.strNumFilter = StrToNumFilterFilter;
    self.crawlerService = CrawlerService;
    self.hours = ["09:00","21:00"];

    getCrawlerTable();

    //取所有已配置的爬虫任务
    function getCrawlerTable () {
        self.crawlerService.getCrawlerTable(function(response){
            self.crawlerTableItem = [];
            for(var i = 0; i<response.length; i++ ){
                var crawlerItem = {};
                crawlerItem['index'] = i+1;
                crawlerItem['name'] = response[i]['crawlerClass'];
                crawlerItem['time'] = response[i]['time'];
                self.crawlerTableItem[i] = crawlerItem;
            }
        });
    }

    //删除任务
    self.delete = function (crawler) {
        self.crawlerService.deleteCrawler(crawler.name, crawler.time, function () {
            alert('successfully deleted');
            getCrawlerTable();
        },function () {
            alert('action failed');
            getCrawlerTable();
        });
    }

    //绑定下拉框
    self.crawlerService.getCrawlerClass(function (response) {
        self.crawlerList=[];
        for (var i = 0; i < response.length; i++) {
            var temp = response[i].split(".");
            var crawler = {}
            crawler['k'] = temp[temp.length - 1];
            crawler['v'] = response[i];
            self.crawlerList[i] = crawler;
        }
    });

    //爬虫列表的CSS
    self.getTrClass = function (index) {
        if (self.strNumFilter(index) % 2 == 0) {
            return {
                oddTr: false,
                evenTr: true
            }
        } else {
            return {
                oddTr: true,
                evenTr: false
            }
        }
    }

    //新增爬虫任务
    self.save = function () {
        var crawler = self.crawler;
        self.crawlerService.saveCrawler(self.crawler, self.hour, function(){
            alert("successfully saved");
            getCrawlerTable();
        },function(){
            alert("action failed");
            getCrawlerTable();
        });
    }
};
