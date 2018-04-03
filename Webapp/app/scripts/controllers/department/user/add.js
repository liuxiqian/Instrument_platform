'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:DepartmentUserAddCtrl
 * @description 用户管理 
 * # DepartmentUserAddCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('DepartmentUserAddCtrl', function($scope, $controller) {
        $controller('UserUserfileAddCtrl', { $scope: $scope });
    });
