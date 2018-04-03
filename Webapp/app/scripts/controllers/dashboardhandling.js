'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:DashboardhandlingCtrl
 * @description
 * # DashboardhandlingCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('DashboardhandlingCtrl', ['$scope',
        'CommonService',
        'WorkService',
        'config',
        function ($scope, CommonService, WorkService, config) {

            var self = this;
            CommonService.initControllerPage(self, $scope);
            WorkService.initController(self, $scope);

            self.init = function () {
                $scope.params = self.initScopeParams();
                self.load();
                $scope.hideAuditDepartment = true;
            };

            self.initScopeParams = function () {
                return {
                    page: 0,
                    size: config.size,
                    sort: 'id,desc'
                };
            };

            self.load = self.reload = function () {
                WorkService.pageHandlingWorkOfCurrentUser($scope.params, function (data) {
                    $scope.data = data;
                });
            };

            self.init();
        }]);
