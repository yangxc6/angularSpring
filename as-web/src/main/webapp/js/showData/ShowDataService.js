/**
 * Created by yxc on 2016/12/6.
 */

var ShowDataService = function (RestService) {
    var self = this;
    self.restService = RestService;

    self.getTenItems = function(crawler,successcb, errorcb){
        this.restService._get('crawler/getTenItems?crawler='+ crawler,successcb, errorcb,false);
    }
}