'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:StandardFileFiledetailaddCtrl
 * @description
 * 添加标准器
 */
angular.module('webappApp')
    .controller('StandardFiledetailaddCtrl', ['$scope', '$stateParams', 'StandardDeviceService', 'CommonService', function ($scope, $stateParams, StandardDeviceService, CommonService) {
        var self = this;

        // 为新增界面初始化代码
        self.init = function () {
            console.log($stateParams);
            $scope.standardDevice = {
                name: '',
                code: '',
                factoryNum: '',
                licenseNum: '',
                manufacturerName: '',
                main: undefined,
                deviceSet: {id: $stateParams.deviceSetId}, //对应的标准装置
                accuracy: '',           // 精度
                measureScale: '',       // 测量范围
                specification: ''       // 型号规格
            };
        };

        //保存并关闭
        self.saveAndClose = function () {
            StandardDeviceService.save($scope.standardDevice, function () {
                CommonService.success();
            });
        };

        self.init();
        //统一暴露方法
        $scope.saveAndClose = self.saveAndClose;
    }]);
