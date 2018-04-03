'use strict';

/**
 * @ngdoc service
 * @name webappApp.instrumentService
 * @description
 * 器具
 * # instrumentService
 * Service in the webappApp.
 */
angular.module('webappApp')
.service('instrumentService', function () {
	var self = this;
	// 获取几种状态信息
	self.getStatus = function() {
		var status = [
			{id: -1, value: '未备案', style: 'info'},
			{id: 0, value: '予以备案', style: 'success'},
			{id: 1, value: '停用', style: 'warning'},
			{id: 2, value: '报废', style: 'warning2'},
			{id: -2, value: '被退回', style: 'danger'}
		];
		return status;
	};
	return {
		getStatus: self.getStatus
	};
});
