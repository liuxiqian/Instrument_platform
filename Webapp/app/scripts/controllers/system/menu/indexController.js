'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:SystemMenuIndexControllerCtrl
 * @description 菜单管理index
 * # SystemMenuIndexControllerCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('SystemMenuIndexControllerCtrl', ['$scope', '$location', 'WebAppMenuService', 'CommonService', '$state', '$timeout',
        function ($scope, $location, WebAppMenuService, CommonService, $state, $timeout) {
            var self = this;

            // 定义显示方法
            self.init = function () {
                // 获取后台数据
                WebAppMenuService.getMenuTree(function (data) {
                    $scope.datas = [];
                    angular.forEach(data, function (value) {
                        $scope.datas.push(value);
                        if (value._children) {
                            angular.forEach(value._children, function (v) {
                                $scope.datas.push(v);
                            });
                        }
                    });
                });
            };

            // 执行获取数据
            self.init();


            self.changeWeight = function (menu) {
                console.log(menu);
            };

            self.add = function () {
                $location.path('/system/MenuAdd');
            };

            // 上移 wight--
            self.up = function (menu) {
                menu.weight--;
                self.updateWeightById(menu.id, menu.weight);
            };


            // 下移 wight++
            self.down = function (menu) {
                menu.weight++;
                self.updateWeightById(menu.id, menu.weight);
            };

            self.time = undefined;

            self.changWeight = function (menu) {
                self.time = Date.now();
                $timeout(function () {
                    if (Date.now() - self.time >= 1000) {
                        self.updateWeightById(menu.id, menu.weight);
                    }
                }, 1000);
            };

            // 更新权重
            self.updateWeightById = function (id, weight) {
                WebAppMenuService.updateWeightById(id, weight)
                    .then(function success() {

                    }, function error(response) {
                        CommonService.httpError(response);
                    });
            };

            // 方法统一暴露
            $scope.add = self.add;
            $scope.getFullName = WebAppMenuService.getFullName;
            $scope.getRoute = WebAppMenuService.getRouteFromMenu;
            $scope.getUrl = WebAppMenuService.getFullUrl;
            $scope.up = self.up;
            $scope.down = self.down;
            $scope.changWeight = self.changWeight;
        }]);
