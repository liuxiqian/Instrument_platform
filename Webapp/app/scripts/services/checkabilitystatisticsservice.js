'use strict';

/**
 * @ngdoc service
 * @name webappApp.CheckAbilityStatisticsService
 * @description
 * # CheckAbilityStatisticsService
 * 器具受检能力统计
 * poshichao
 */
angular.module('webappApp')
  .service('CheckAbilityStatisticsService', function ($http, CommonService) {
    var self = this;

    self.getAllBySpecification = function(params, callback) {
    	// 按查询条件进行查找
        var url = "/CheckAbilityStatistics/getAllBySpecification";
        $http.get(url, {params: params})
        	.then(function success(response) {
        		if (callback) {
        			callback(response.data);
        		}
        	}, function error(response) {
        		// 请求失败,统一处理
        		CommonService.httpError(response);
        	});
    };

    // 数据初始化(测试使用)
    self.dataInit = function(callback) {
    	var url = "/CheckAbilityStatistics/dataInit";
    	$http.get(url)
    	.then(function success() {
    		if (callback) {
    			callback();
    		}
    	}, function error(response) {
    		CommonService.httpError(response);
    	});
    };

    // 该函数访问后台,用来实现询问函数是否执行完成
    // 完成: 返回true
    // 未完成: 返回false
    self.hasFinished = function(callback) {
    	var url = "/CheckAbilityStatistics/hasFinished";
    	$http.get(url)
    	.then(function success(response) {
    		if (callback) {
    			callback(response);
    		}
    	}, function error(response) {
    		CommonService.httpError(response);
    	});
    };

    // 通过区域和学科类别进行查询
    self.getAllByDistrictAndDiscipline = function(params, callback) {
    	// 定义路由
    	var url = "/CheckAbilityStatistics/getAllByDistrictAndDiscipline";

    	// 发起http请求
    	$http.get(url, {params: params})
    	.then(function success(response) {
    		if (callback) {
    			callback(response);
    		}
    	}, function error(response) {
    		CommonService.httpError(response);
    	});
    };

    // 通过区域和器具类别进行查询
    self.getAllByDistrictAndInstrumentType = function(params, callback) {
    	// 定义路由
    	var url = "/CheckAbilityStatistics/getAllByDistrictAndInstrumentType";

    	// 发起http请求
    	$http.get(url, {params: params})
    		.then(function success(response) {
    			if (callback) {
    				callback(response);
    			}
    		}, function error(response) {
    			CommonService.httpError(response);
    		})
    }

    return {
    	getAllBySpecification: self.getAllBySpecification,
    	dataInit: self.dataInit,
    	hasFinished: self.hasFinished,
    	getAllByDistrictAndDiscipline: self.getAllByDistrictAndDiscipline,
    	getAllByDistrictAndInstrumentType: self.getAllByDistrictAndInstrumentType
    };
  });
