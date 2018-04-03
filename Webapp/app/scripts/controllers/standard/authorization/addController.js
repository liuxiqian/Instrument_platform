'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:StandardAuthorizationAddCtrl
 * @description
 * # StandardAuthorizationAddCtrl
 * Controller of the webappApp
 * 授权检定项目增加Controller
 */
angular.module('webappApp')
    .controller('StandardAuthorizationAddCtrl', ['$scope',
        'StandardAuthorizationService',
        '$stateParams',
        'CommonService',
        function($scope,
            StandardAuthorizationService,
            $stateParams,
            CommonService) {
            var self = this;
            self.init = function() {
                self.data = {};
                self.data.deviceSet = { id: $stateParams.id };
                self.data.accuracy = {}; // 对应的精度
                self.data.measureScale = {}; // 对应的测量范围
                self.data.instrumentType = {}; // 对应的器具类别
                $scope.data = self.data;
                $scope.discipline = {};
            };

            // 保存并关闭
            self.saveAndClose = function() {
                StandardAuthorizationService.save($scope.data, function() {
                    CommonService.success();
                });
            };

            // 统一暴露方法
            self.init();
            $scope.saveAndClose = self.saveAndClose;
        }
    ]);
