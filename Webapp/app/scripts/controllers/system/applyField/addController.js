'use strict';

/**
 * @ngdoc function是否为必填字段
 * @name webappApp.controller:SystemApplyFieldAddCtrl
 * @description     系统设置-申请类型对应的申请字段添加功能
 * # SystemApplyFieldAddCtrl
 * @author zhangjiahao
 */
angular.module('webappApp')
    .controller('SystemApplyFieldAddCtrl', ['$scope', 'ApplyFieldService', 'CommonService', '$stateParams', function($scope, ApplyFieldService, CommonService, $stateParams) {
        // 数据初始化
        var self = this;
        self.applyTypeId = parseInt($stateParams.applyTypeId ? $stateParams.applyTypeId : 0); //获取申请类型id
        // 新增初始化
        self.addInit = function () {
            $scope.data = {
                name: '',
                label: '',
                description: '',
                weight: '',
                type: '',
                required: '',
                warnMessage: '',
                applyType:{
                    id: self.applyTypeId
                }
            };
        };
        self.addInit();

        self.update = function () {
            ApplyFieldService.save($scope.data, function () {
                CommonService.success();
            });
        };
        $scope.update = self.update;
    }]);
