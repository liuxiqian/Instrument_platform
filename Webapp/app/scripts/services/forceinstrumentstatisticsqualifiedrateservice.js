'use strict';

/**
 * @ngdoc service
 * @name webappApp.forceInstrumentStatisticsQualifiedrateService
 * @description
 * # forceInstrumentStatisticsQualifiedrateService
 * Service in the webappApp.
 * 器具合格率查询
 * zhuchenshu
 */
angular.module('webappApp')
  .service('forceInstrumentStatisticsQualifiedrateService', function ($http, CommonService) {
  		var self = this;
  		// 查询选择年度及1个区域主体的数据
  		self.quaryByYearsAndDistrictAndParams = function (years, district, params, callback) {
  			var baseUrl = 'years/' + years + '/district/' + district.id; // 基本查询url拼接
  			var url = '/ForceInstrumentStatisticsQualifiedRate/' + baseUrl;
  			$http.get(url, {params : params}).then(function success(response) {
  				if (callback) {
  					callback(response.data);
  				}
  			}, function error(response) {
  				CommonService.httpError(response);
  			});
  		};
  		// 查询选择年度及1个区域主体和器具用户的数据
  		self.quaryByYearsAndDistrictAndDepartmentAndParams = function (years, district, department, params, callback) {
  			var baseUrl = 'years/' + years + '/district/' + district.id + '/department/' + department.id; // 基本查询url拼接
  			var url = '/ForceInstrumentStatisticsQualifiedRate/' + baseUrl;

  			$http.get(url, {params : params}).then(function success(response) {
  				if (callback) {
  					callback(response.data);
  				}
  			}, function error(response) {
  				CommonService.httpError(response);
  			});
  		};

  		return {
  			quaryByYearsAndDistrictAndParams : self.quaryByYearsAndDistrictAndParams,
  			quaryByYearsAndDistrictAndDepartmentAndParams : self.quaryByYearsAndDistrictAndDepartmentAndParams
  		};
  });
