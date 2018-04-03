'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:StandardFileCheckdetailaddCtrl
 * @description
 * # StandardFileCheckdetailaddCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('StandardFileCheckdetailAddCtrl', ['$scope', 'StandardDeviceCheckDetailService', '$stateParams', 'CommonService', function ($scope, StandardDeviceCheckDetailService, $stateParams, CommonService) {
        var self = this;

        //为新增界面初始化数据
        self.addInit = function () {
            $scope.data = {
                calibrationDepartment: '',     //检定部门
                certificateNum: '',            //证书编号
                checkDate: '',                 //检定日期
                validityDate: '',              //有效期至
                alertDate: '',                 //报警日期
                inspectorQualifierCertificate: '',     //检定员
                verifierQualifierCertificate: '',      //核验员
                correctValue: '',                      //修正值
                checkResult: {},                       //检定结果
                standardDevice: {
                    id: $stateParams.standardDeviceId
                }       //标准器
            };
        };

        //获取所有的检定结果
        self.getCheckResultTree = function () {
            StandardDeviceCheckDetailService.getCheckResultTree(function (data) {
                $scope.checkResults = data;
            });
        };
        self.getCheckResultTree();

        //保存
        self.save = function (callback) {
            StandardDeviceCheckDetailService.save($scope.data, callback);
        };

        //保存并关闭
        self.saveAndClose = function () {
            self.save(function () {
                CommonService.success();
            });
        };

        //保存并新建
        self.saveAndNew = function () {
            self.save(function () {
                CommonService.success();
            });
        };

        self.addInit();
        //统一暴露方法
        $scope.saveAndNew = self.saveAndNew;
        $scope.saveAndClose = self.saveAndClose;
    }]);
