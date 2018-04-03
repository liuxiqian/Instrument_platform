'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:StandardAuthorizationEditCtrl
 * @description
 * # 授权检定项目 -- 编辑
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('StandardAuthorizationEditCtrl', function($scope, $controller, StandardAuthorizationService) {
        var self = this;
        angular.extend(self, $controller('StandardAuthorizationAddCtrl', {$scope: $scope}));
        StandardAuthorizationService.setDeviceSet(self.deviceSet);

        // TODO调用http请求，获取实体
    
    });
