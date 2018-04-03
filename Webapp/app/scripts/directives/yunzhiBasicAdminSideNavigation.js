'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:basicAdminSideNavigation
 * @description
 * 基础模板的左侧菜单
 * # basicAdminSideNavigation
 */
angular.module('webappApp')
.directive('yunzhiBasicAdminSideNavigation', function (WebAppMenuService) {
	return {
		templateUrl: 'views/directive/basicAdminSideNavigation.html',
		restrict: 'EA',
		link: function postLink(scope) {
			var self = this;
			WebAppMenuService.sideNavigationInit(self, scope, function(){
			});
		}
	};
});
