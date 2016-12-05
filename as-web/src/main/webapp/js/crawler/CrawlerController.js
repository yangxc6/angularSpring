/**
 * Created by yxc on 2016/12/5.
 */

var CrawlerController = function (StrToNumFilterFilter) {
    this.crawlerList = [
        {index:1, name:'JXGT'},
        {index:2, name:'empty'}
    ];

    this.getTrClass = function(index){
        if(StrToNumFilterFilter(index)%2 ==0){
            return{
                oddTr:false,
                evenTr:true
            }
        }else{
            return{
                oddTr:true,
                evenTr:false
            }
        }
    }
}
