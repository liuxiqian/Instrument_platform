'use strict';

/**
 * @ngdoc filter
 * @name webappApp.filter:nullDataFilter
 * @function
 * @description
 * # nullDataFilter
 * Filter in the webappApp.
 * zhuchsnhu
 * 空数据过滤器
 */
angular.module('webappApp')
  .filter('nullDataFilter', function () {
  	var self = {};

  	self.getNullData = function(input) {
  		if (!input || input === '请选择') {
    		return '---' ;
    	} else {
    		return input;
    	}
  	};

    return function (input) {
    	return self.getNullData(input);
    };
  });
