'use strict';

/** 
 * @ngdoc function 
 * @name webappApp.controller:DashboardovertimeCtrl 
 * @description 
 * # DashboardovertimeCtrl 
 * Controller of the webappApp 
 */
angular.module('webappApp')
    .controller('DashboardovertimeCtrl', ['$scope',
        'WorkService',
        function($scope, WorkService) {
            var self = this;

            // 实始化
            self.init = function() {
                self.reloadByPage(0);
            };

            /**
             * 重新请求数据
             * @param  {int} page 当前页
             * @return 
             */
            self.reloadByPage = function(page) {
                // 初始化分页参数，并发起请求
                var param = {page:page, size: 10};
                WorkService.getAllCurrentUserWorkOfOverTime(param, function(data) {
                    $scope.data = data;
                });
            };

            $scope.reloadByPage = self.reloadByPage;
            self.init();
        }
    ]);

