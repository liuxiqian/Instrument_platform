'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MyworkHandlingIndexCtrl
 * @description
 * # MyworkHandlingIndexCtrl
 * 在办工作
 */
angular.module('webappApp')
    .controller('MyworkHandlingIndexCtrl', ['$scope', 'WorkService', 'CommonService',
        function ($scope, WorkService, CommonService) {
            var self = this;
            CommonService.initControllerPage(self, $scope);

            self.init = function () {
                WorkService.initController(self, $scope);
                self.load();
            };


            //获取在办工作数据
            self.load = self.reload = function () {
                WorkService.pageHandlingWorkOfCurrentUser(self.generateQueryParams(), function (data) {
                    $scope.data = data;
                });
            };

            self.init();
        }]);
