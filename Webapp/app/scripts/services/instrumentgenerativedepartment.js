'use strict';

/**
 * @ngdoc service
 * @name webappApp.InstrumentGenerativeDepartment
 * @description
 * # InstrumentGenerativeDepartment
 * Service in the webappApp.
 * zhuchenshu
 * 获取所有的生产企业
 */
angular.module('webappApp')
  .service('InstrumentGenerativeDepartment', function ($http, CommonService) {
  		var self = this;

  		// 获取所有的生产单位
  		self.getAllGenerativeDepartment = function(callback) {
  			var url = '/MandatoryInstrument/getAllGenerativeDepartment';

  			$http.get(url).then(function success(response) {
  				if (callback) {
  					callback(response.data);
  				}
  			}, function error(response) {
  					CommonService.httpError(response);
  			});
  		};

  		return {
  			getAllGenerativeDepartment: self.getAllGenerativeDepartment
  		};
  });
