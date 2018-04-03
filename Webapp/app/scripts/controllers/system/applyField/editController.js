'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:SystemApplyFieldEditCtrl
 * @description     系统设置-申请类型-申请字段管理 编辑
 * # SystemApplyFieldEditCtrl
 * @author zhangjiahao
 */
angular.module('webappApp')
    .controller('SystemApplyFieldEditCtrl', ['$scope', 'ApplyFieldService', 'CommonService', '$stateParams', function($scope, ApplyFieldService, CommonService, $stateParams) {
        // 数据初始化
        var self = this;
        self.id = parseInt($stateParams.id ? $stateParams.id : 0); //获取申请字段id

        self.getOneById = function () {
            ApplyFieldService.getOneById(self.id, function (data) {
                $scope.data = data;
            });

        };
        self.getOneById();

        self.update = function() {
            ApplyFieldService.update($scope.data.id, $scope.data, function () {
                CommonService.success();
            });
        };

        $scope.update = self.update;
    }]);
