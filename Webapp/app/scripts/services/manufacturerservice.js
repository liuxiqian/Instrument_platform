'use strict';

/**
 * @ngdoc service
 * @name webappApp.ManufacturerService
 * @description 制造单位（生产企业）
 * # ManufacturerService
 * Service in the webappApp.
 */
angular.module('webappApp')
.service('ManufacturerService', ['$http', function ($http, CommonService) {
	var self = this;
	/**
	 * 获取当前用户可见的企业信息
	 * @param callback
	 */
	self.getCurrentUserManufacturerArray = function (callback) {
		$http.get('data/manufacturer/getCurrentUserManufacturerArray.json').then(function (response) {
			callback(response.data);
		});
	};
	
	/**
	 * 获取前10条名称包含name的生产企业（制造单位）信息
	 * @param name 模糊查询的名称
	 * @param callback
	 * @author panjie
	 */
	self.getTop10ByNameContaining = function(name, callback) {
		if (name !== '') {
			var url = '/ManufacturerDepartment/getTop10ByNameContaining/' + name;
			$http.get(url)
			.then(function success(response){
				if (callback) {callback(response.data);}
			}, function error(response){
                CommonService.httpError(response);
			});
		} else {
			callback([]);
		}
	};
	
	return {
		getCurrentUserManufacturerArray: self.getCurrentUserManufacturerArray,
		getTop10ByNameContaining: self.getTop10ByNameContaining
	};
}]);
