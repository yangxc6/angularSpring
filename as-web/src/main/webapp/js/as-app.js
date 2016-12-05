/**
 * Created by yxc on 2016/12/5.
 */

var app = angular.module('as-app', ['ui.router']);

app.filter('StrToNumFilter',[StrToNumFilter])
    .controller('CrawlerController', ['StrToNumFilterFilter', CrawlerController]);

app.config(function ($stateProvider, $urlRouterProvider) {
    //默认页面
    $urlRouterProvider.when("/", "/crawler").otherwise("/crawler");

    $stateProvider.state('crawler', {
        url : "/crawler",
        templateUrl : 'templates/mainContent/crawler.html',
        controller : 'CrawlerController'
    });
});