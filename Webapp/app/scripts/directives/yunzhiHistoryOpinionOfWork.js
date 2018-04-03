'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiGetHistoryOpinionOfWork
 * @description
 * # yunzhiGetHistoryOpinionOfWork
 * 获取某个工作的历史审核意见
 */
angular.module('webappApp')
.directive('yunzhiHistoryOpinionOfWork', function (WorkService) {
	return {
		scope: {
			ngModel: '='
		},
		templateUrl: 'views/directive/yunzhiHistoryOpinionOfWork.html',
		restrict: 'EA',
		controller: function($scope) {
			
			var self = {};
			$scope.works = [];
			
			self.getWorksByWork = function(work) {
				WorkService.getAllByApplyId(work.apply.id, function(data){
					$scope.works = data;
				});
			};
			
			self.watchNgModel = function(ngModel) {
				self.getWorksByWork(ngModel);
			};

			$scope.$watch('ngModel', function(newValue) {
				if (newValue) {
					self.watchNgModel(newValue);	
				}
			});
		}
	};
});
