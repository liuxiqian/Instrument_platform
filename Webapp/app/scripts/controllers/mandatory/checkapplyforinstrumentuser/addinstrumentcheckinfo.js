'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatoryCheckapplyforinstrumentuserAddinstrumentcheckinfoCtrl
 * @description
 * # MandatoryCheckapplyforinstrumentuserAddinstrumentcheckinfoCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('MandatoryCheckapplyforinstrumentuserAddinstrumentcheckinfoCtrl',
        function ($scope, $stateParams, InstrumentCheckInfoService, CommonService) {
            var self = this;

            // 初始化
            self.init = function () {
                $scope.data = self.initScopeData();
                if (!self.validate()) {
                    CommonService.error('系统错误', '未接收到检定申请信息或器具信息');
                }

                // 统一暴露
                $scope.saveAndClose = self.saveAndClose;
            };

            self.initScopeData = function () {
                return {
                    certificateNum: '',            //证书编号
                    checkDate: '',                 //检定日期
                    validityDate: '',              //有效期至
                    checkResult: {},               //检定结果
                    instrumentCertificateType: {},           //器具证书类型
                    mandatoryInstrument: $stateParams.mandatoryInstrument,  // 强检器具
                    mandatoryInstrumentCheckApply: {id: $stateParams.mandatoryInstrumentCheckApplyId},            // 检定申请
                    checkDepartment: {name: ''}                             // 检定技术机构名称
                };
            };

            self.validate = function () {
                if (!$scope.data.mandatoryInstrumentCheckApply.id || !$scope.data.mandatoryInstrument.id) {
                    return false;
                } else {
                    return true;
                }
            };

            // 保存并关闭
            self.saveAndClose = function () {
                InstrumentCheckInfoService.saveViaInstrumentUserDepartment($scope.data, function () {
                    CommonService.success();
                });
            };
            self.init();


        });
