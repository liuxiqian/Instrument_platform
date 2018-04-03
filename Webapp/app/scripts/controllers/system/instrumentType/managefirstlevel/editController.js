'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:SystemInstrumenttypeManageFirstLevelEditCtrl
 * @description     系统设置-强检器具（标准器）类别管理-管理一级分类-编辑
 * # SystemInstrumenttypeManageFirstLevelEditCtrl
 * @author zhangjiahao
 */
angular.module('webappApp')
    .controller('SystemInstrumenttypeManageFirstLevelEditCtrl', ['$scope', 'InstrumentFirstLevelTypeService', 'CommonService', '$stateParams', function($scope, InstrumentFirstLevelTypeService, CommonService, $stateParams) {
        // 数据初始化
        var self = this;
        self.manageFirstLevelId = parseInt($stateParams.id ? $stateParams.id : 0); //获取分类一级名称id

        self.getOneByManageFirstLevelId = function () {
            InstrumentFirstLevelTypeService.getOneByManageFirstLevelId(self.manageFirstLevelId, function (data) {
                $scope.data = data;
            });

        };
        self.getOneByManageFirstLevelId();

        self.update = function() {
            InstrumentFirstLevelTypeService.update($scope.data.id, $scope.data, function () {
                CommonService.success();
            });
        };

        $scope.update = self.update;
    }]);
