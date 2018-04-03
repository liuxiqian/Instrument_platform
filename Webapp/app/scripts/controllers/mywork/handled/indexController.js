'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MyworkHandledIndexCtrl
 * @description
 * # MyworkHandledIndexCtrl
 * 已办工作
 */
angular.module('webappApp')
    .controller('MyworkHandledIndexCtrl', ['WorkService', '$scope', 'CommonService',
        function (WorkService, $scope, CommonService) {
            var self = this;
            CommonService.initControllerPage(self, $scope);

            self.init = function () {
                WorkService.initController(self, $scope);
                self.load();
            };

            //获取在办工作数据
            self.load = self.reload = function () {
                WorkService.pageHandledWorkOfCurrentUser(self.generateQueryParams(), function (data) {
                    $scope.data = data;
                });
            };

            self.init();
        }]);
