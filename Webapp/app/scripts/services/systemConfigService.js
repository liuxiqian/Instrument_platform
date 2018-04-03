'use strict';

/**
 * @ngdoc service
 * @name webappApp.SystemConfigService
 * @description
 * # SystemConfigService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('SystemConfigService', function($http, CommonService) {
        var self = this;

        /**
         * 获取某个KEY对应的系统设置值
         * @param  {string}   key      键
         * @param  {Function} callback
         * @return {string | undefined}            找到，则返回值。未找到，则不返回任何信息，即：undefined
         */
        self.getByKey = function(key, callback) {
        	self.getAll(function(datas) {
        		var unFounded = true;
        		var foundValue;
        		angular.forEach(datas, function(value){
        			if (unFounded && value.k === key) {
        				unFounded = false;
        				foundValue = value.value;
        			}
        		});
        		if (callback) {callback(foundValue);}
        	});
        };


        /**
         * 获取系统设置的所有数据，并不进行重复请求
         * @param  {Function} callback
         * @return {array}
         */
        self.getAll = function(callback) {
            if (!self.getAllData) {
                var url = '/SystemConfig/getAll';
                $http.get(url)
                    .then(function success(response) {
                    	self.getAllData = response.data;
                    	if (callback) {callback(self.getAllData);}
                    }, function error(response) {
                        CommonService.httpError(response);
                    });
            } else {
                if (callback) {callback(self.getAllData);}
            }

        };

        return {
            getByKey: self.getByKey
        };
        // AngularJS will instantiate a singleton by calling "new" on this function
    });
