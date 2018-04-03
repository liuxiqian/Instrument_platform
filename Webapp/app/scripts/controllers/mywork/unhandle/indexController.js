'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MyworkUnhandleIndexCtrl
 * @description
 * # MyworkUnhandleIndexCtrl
 * 待办工作
 */
angular.module('webappApp')
    .controller('MyworkUnhandleIndexCtrl', ['$scope', 'WorkService', 'CommonService',
        function ($scope, WorkService, CommonService) {
            var self = this;
            CommonService.initControllerPage(self, $scope);

            self.init = function () {
                WorkService.initController(self, $scope);
                self.load();
            };

            self.load = self.reload = function () {
                //获取未办结工作数据
                WorkService.pageUnHandleWorkOfCurrentUser(self.generateQueryParams(), function (data) {
                    $scope.data = data;
                });
            };

            self.init();
        }]);
