'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:DashboardunhandlCtrl
 * @description
 * # DashboardunhandlCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('DashboardunhandlCtrl', ['$scope',
        'WorkService',
        'CommonService',
        'config',
        function ($scope, WorkService, CommonService, config) {
            var self = this;
            CommonService.initControllerPage(self, $scope);
            WorkService.initController(self, $scope);

            //获取所有的数据
            self.init = function() {
                $scope.params = self.initScopeParams();
                self.load();
                $scope.hideAuditDepartment = true;
            };

            self.initScopeParams = function() {
                return {
                    page: 0,
                    size: config.size,
                    sort: 'id,desc'
                };
            };

            self.load = self.reload = function() {
                WorkService.pageUnHandleWorkOfCurrentUser($scope.params, function (data) {
                    $scope.data = data;
                });
            };

            self.init();

        }]);
