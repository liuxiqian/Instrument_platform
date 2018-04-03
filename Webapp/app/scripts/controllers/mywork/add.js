'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MyworkAddCtrl
 * @description 我的工作 新建工作
 * # MyworkAddCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('MyworkAddCtrl', function($scope, $stateParams, ApplyTypeService) {
        var self = this;

        self.init = function() {
            $scope.data = {};
            $scope.data.applyType = { id: $stateParams.applyTypeId };
            ApplyTypeService.getById($scope.data.applyType.id, function(applyType) {
            	$scope.data.applyType = applyType;
            });
        };

        self.init();
    });
