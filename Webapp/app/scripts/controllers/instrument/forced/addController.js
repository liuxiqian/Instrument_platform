'use strict';

/**
 * @ngdoc function
 * @名称 webappApp.controller:MandatoryIntegratedAddCtrl
 * @描述：强检器具添加(备案)
 * # MandatoryIntegratedAddCtrl
 * Controller of the webappApp（控制层）
 */
angular.module('webappApp')
    .controller('InstrumentForcedAddCtrl', [
        '$location',
        '$scope',
        '$timeout',
        'CommonService',
        '$stateParams',
        'mandatoryInstrumentService',
        'ApplyService',
        'WorkService',
        'MandatoryInstrumentWorkService',
        '$state',
        'instrumentStatus',
        function ($location,
                  $scope,
                  $timeout,
                  CommonService,
                  $stateParams,
                  mandatoryInstrumentService,
                  ApplyService,
                  WorkService,
                  MandatoryInstrumentWorkService,
                  $state,
                  instrumentStatus) {
            var self = this;

            // 初始化
            self.addInit = function () {

                self.mandatoryInstrumentList = $stateParams.mandatoryInstrumentList;
                $scope.data = {status: instrumentStatus.new};
                $scope.data.attachments = [];
            };

            // 提交
            self.submit = function () {
                if ($scope.submitType === 'saveAndNew') {
                    self.saveAndNew();
                } else {
                    self.saveAndClose();
                }
            };

            // 保存
            self.save = function (callback) {
                var data = {};
                angular.copy($scope.data, data);
                self.mandatoryInstrumentList.push(data);
                console.log(self.mandatoryInstrumentList);
                if (callback) {
                    callback();
                }
            };

            //  保存强检器具
            self.saveAndClose = function () {
                self.save(function () {
                    CommonService.success('操作成功', '', function () {
                        CommonService.deleteCurrentState();
                        $state.transitionTo('InstrumentForced.InstrumentForcedApply.addApply', {mandatoryInstrumentList: self.mandatoryInstrumentList});
                    });
                });
            };

            self.saveAndNew = function () {
                self.save(function () {
                    CommonService.success('操作成功', '', function () {
                        // 带有参数跳转到编辑路由
                        $scope.data.serialNum = '';
                        $scope.IntegratedAdd.$submitted = false;
                    });
                });
            };

            // 改变用途
            self.changePurpose = function (object) {
                $scope.data.purpose.name = object.name;
            };

            // 改变适用规格型号
            self.changeSpecification = function (object) {
                $scope.data.specification.name = object.name;
            };

            // 改变精确度
            self.changAccuracy = function (object) {
                $scope.data.accuracy.value = object.value;
            };

            // 测量范围
            self.changeMeasureScale = function (object) {
                $scope.data.measureScale.value = object.value;
            };

            // 统一暴露
            self.addInit();
            $scope.console = console;
            $scope.submit = self.submit;
            $scope.changAccuracy = self.changAccuracy;
            $scope.changePurpose = self.changePurpose;
            $scope.changeMeasureScale = self.changeMeasureScale;
            $scope.changeSpecification = self.changeSpecification;
            $scope.notFoundAccuracy = '准确度等级'; // 用户未找到需要的精确度的值
            $scope.notFoundMeasureScale = '测量范围'; // 用户未找到需要的测量范围的值
        }
    ]);
