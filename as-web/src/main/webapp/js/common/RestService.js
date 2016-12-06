/**
 * Created by yxc on 2016/12/5.
 */

function RestService($http) {
    var self = this;
    self.$http = $http;

    self._get = function(url, successcb, errorcb, cached) {
        var isCached = cached || false;
        httpPromise = self.$http.get(encodeURI(url), {cache: isCached});
        return self._thenFactoryMethod(httpPromise, successcb, errorcb);
    };

    self._post = function(url, data, successcb, errorcb, options) {
        data = data || {}
        var postOptions = options || {}
        httpPromise = self.$http.post(encodeURI(url), data, postOptions);
        return self._thenFactoryMethod(httpPromise, successcb, errorcb);
    };

    this._put = function(url, data, successcb, errorcb) {
        data = data || {}
        httpPromise = this.$http.put(encodeURI(url), data);
        return this._thenFactoryMethod(httpPromise, successcb, errorcb);
    };

    this._delete = function(url, successcb, errorcb) {
        httpPromise = this.$http.delete(encodeURI(url));
        return this._thenFactoryMethod(httpPromise, successcb, errorcb);
    };

    this._thenFactoryMethod = function(httpPromise, successcb, errorcb) {
        var scb = successcb || angular.noop;
        var ecb = errorcb || angular.noop;


        return httpPromise.success(function(data, status, headers, config) {

            if(typeof data['status'] == "undefined"){
            //    window.location = "login";
            }

            if(data['status'] == "SUCCESS") {
                scb(data['result'], status, headers, config);
            } else {
                ecb(data['result'], status, headers, config);
            }
        }).error(function(data, status, headers, config) {
            var errorMessage = "内部服务器故障，请稍后再试！"
            ecb(errorMessage, status, headers, config);
        });
    }
}
