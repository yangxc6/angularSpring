/**
 * Created by yxc on 2016/12/6.
 */

var ShowDataController = function(StrToNumFilterFilter,CrawlerService,ShowDataService){
    var self = this;
    self.strNumFilter = StrToNumFilterFilter;
    self.crawlerService = CrawlerService;
    self.showDataService = ShowDataService;

    self.crawlerService.getCrawlerClass(function(response){
        self.crawlerList=[];
        for (var i = 0; i < response.length; i++) {
            var temp = response[i].split(".");
            var crawler = {};
            crawler['k'] = temp[temp.length - 1];
            crawler['v'] = response[i];
            self.crawlerList[i] = crawler;
        }
    });

    self.getTrClass = function(index){
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

    self.updateTable = function (crawler) {
        self.showDataService.getTenItems(crawler, function(response){
            self.TableItem = [];
            for(var i = 0; i<response.length; i++){
                var obj = {};
                obj['index'] = i+1;
                obj['title'] = response[i];
                self.TableItem[i] = obj;
            }
        });
    }
}