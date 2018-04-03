'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MyworkHoldonIndexCtrl
 * @description
 * # MyworkHoldonIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('MyworkDoneIndexCtrl', ['WorkService', '$scope', 'CommonService',
        function (WorkService, $scope, CommonService) {
            var self = this;
            CommonService.initControllerPage(self, $scope);

            self.init = function () {
                WorkService.initController(self, $scope);
                self.load();
            };

            //获取未办结工作数据
            self.load = self.reload = function () {
                WorkService.pageDoneWorkOfCurrentUser(self.generateQueryParams(), function (data) {
                    $scope.data = data;
                });
            };

            self.init();
        }]);
