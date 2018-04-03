'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:SystemInstrumenttypeManageFirstLevelAddCtrl
 * @description     系统设置-强检器具（标准器）类别管理-管理一级分类-增加
 * # SystemInstrumenttypeManageFirstLevelAddCtrl
 * @author zhangjiahao
 */
angular.module('webappApp')
    .controller('systemApplyTypeAddCtrl', ['$scope', 'ApplyTypeService', 'CommonService', function($scope, ApplyTypeService, CommonService) {
        // 数据初始化
        var self = this;
        // 新增初始化
        self.addInit = function () {
            $scope.data = {
                name: '',
                pinyin: '',
            };
        };
        self.addInit();

        self.update = function () {
            ApplyTypeService.save($scope.data, function () {
                CommonService.success();
            });
        };
        $scope.update = self.update;
    }]);
