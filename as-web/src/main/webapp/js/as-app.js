/**
 * Created by yxc on 2016/12/5.
 */

var app = angular.module('as-app', ['ui.router']);

app.filter('StrToNumFilter',[StrToNumFilter])
    .service('RestService',['$http',RestService])
    .service('CrawlerService',['RestService', CrawlerService])
    .service('ShowDataService',['RestService', ShowDataService])
    .controller('CrawlerController', ['StrToNumFilterFilter','CrawlerService', CrawlerController])
    .controller('ShowDataController', ['StrToNumFilterFilter','CrawlerService','ShowDataService', ShowDataController]);

app.config(function ($stateProvider, $urlRouterProvider) {
    //默认页面
    $urlRouterProvider.when("/", "/crawler").otherwise("/crawler");

    $stateProvider.state('crawler', {
        url : "/crawler",
        templateUrl : 'templates/mainContent/crawler.html',
        controller : 'CrawlerController'
    })
        .state('showData',{
            url : "/showData",
            templateUrl : 'templates/mainContent/showData.html',
            controller : 'ShowDataController'
        });
});