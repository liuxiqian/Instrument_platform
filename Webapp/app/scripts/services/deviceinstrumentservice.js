'use strict';

/**
 * @ngdoc service
 * @name webappApp.DeviceInstrumentService
 * @description
 * # DeviceInstrumentService
 * Service in the webappApp.
 */
angular.module('webappApp')
.service('DeviceInstrumentService', function ($http, CommonService) {
	var self = this;
	self.pageByDeviceSetIdOfCurrentUser = function (deviceSetId, params, callback) {
        var url = '/DeviceInstrument';
	    if (typeof (deviceSetId) !== 'undefined') {
	        // 根据deviceSet获取
	        url += '/pageByDeviceSetIdOfCurrentUser/' + deviceSetId;
        } else {
	        // 获取所有
	        url += '/pageOfCurrentUser';
        }

		$http.get(url, {params: params})
		.then(function success(response) {
			if (callback) {
				callback(response.data);
			}
		}, function error(response) {
            CommonService.httpError(response);
		});
	};

	self.delete = function (id, callback) {
		var url = '/DeviceInstrument/' + id;

		//向后台发出请求
		$http.delete(url)
		.then(function success(response) {
			if (callback) {
				callback(response.status);
			}
		}, function error(response) {
            CommonService.httpError(response);
		});
	};

	return {
		pageByDeviceSetIdOfCurrentUser: self.pageByDeviceSetIdOfCurrentUser,
		delete: self.delete
	};
});
