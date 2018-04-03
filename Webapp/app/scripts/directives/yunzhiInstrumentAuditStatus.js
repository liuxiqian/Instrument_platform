'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiInstrumentAuditStatus
 * @description
 * # yunzhiInstrumentAuditStatus
 */
angular.module('webappApp')
.directive('yunzhiInstrumentAuditStatus', function () {
	return {
		scope: {
			ngModel: '='
		},
		templateUrl: 'views/directive/yunzhiInstrumentAuditStatus.html',
		restrict: 'EA'
	};
});
