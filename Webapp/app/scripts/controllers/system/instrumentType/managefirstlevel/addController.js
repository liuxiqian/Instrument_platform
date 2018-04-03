'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:SystemInstrumenttypeManageFirstLevelAddCtrl
 * @description     系统设置-强检器具（标准器）类别管理-管理一级分类-增加
 * # SystemInstrumenttypeManageFirstLevelAddCtrl
 * @author zhangjiahao
 */
angular.module('webappApp')
    .controller('SystemInstrumenttypeManageFirstLevelAddCtrl', ['$scope', 'InstrumentFirstLevelTypeService', 'CommonService', '$stateParams', function($scope, InstrumentFirstLevelTypeService, CommonService, $stateParams) {
        // 数据初始化
        var self = this;
        self.disciplineId = parseInt($stateParams.disciplineId ? $stateParams.disciplineId : 0); //获取分类一级名称id
        console.log(self.disciplineId);
       // 新增初始化
        self.addInit = function () {
            $scope.data = {
                name: '',
                pinyin: '',
                discipline:{
                    id: self.disciplineId
                }
            };
        };
        self.addInit();

        self.update = function () {
            InstrumentFirstLevelTypeService.save($scope.data, function () {
                CommonService.success();
            });
        };
        $scope.update = self.update;
    }]);
