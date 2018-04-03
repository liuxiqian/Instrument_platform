'use strict';

/**
 * @ngdoc service
 * @name webappApp.MandatoryInstrumentTypeService
 * @description
 * 强制检定器具类别
 * # MandatoryInstrumentTypeService
 * Service in the webappApp.
 */
angular.module('webappApp')
.service('MandatoryInstrumentTypeService', function ($http, CommonService) {
	var self = this;

	// 获取某个一级类别下的所有信息
	self.getAllByInstrumentFirstLevelTypeId = function(instrumentFirstLevelTypeId, callback) {
		var url = '/MandatoryInstrumentType/getAllByInstrumentFirstLevelTypeId/' + instrumentFirstLevelTypeId;
		$http.get(url)
		.then(function success(response){
			if (callback) {callback(response.data);}
		}, function error(response){
            CommonService.httpError(response);
		});
	};

	/**
     * 获取强检器具二级类别列表
     * @param  {Function} callback [description]
     * @return {[type]}            [description]
     * zhangxishuo
     */
    self.getAll = function(callback) {
        var url = '/MandatoryInstrumentType/getAll';  // 定义url
        $http.get(url)                                // 请求
            .then(function success(response) {
                if (callback) {
                    callback(response.data);          // 回调
                }
            }, function error(response) {
                CommonService.httpError(response);    // 统一错误处理
            });
    };

	return {
		getAll: self.getAll,
		getAllByInstrumentFirstLevelTypeId: self.getAllByInstrumentFirstLevelTypeId
	};
});
