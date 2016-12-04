/**
 * Created by yxc on 2016/12/5.
 */

var app = angular.module('as-app', []);

app.config(function ($stateProvider, $urlRouterProvider) {
    //默认页面
    $urlRouterProvider.when("/", "/crawler").otherwise("/crawler");

    $stateProvider.state('crawler', {
        url : "/crawler",
        templateUrl : 'template/mainContent/crawler.html',
        controller : 'CrawlerController'
    });
});