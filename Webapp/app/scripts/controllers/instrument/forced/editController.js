'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:InstrumentForcedEditcontrollerCtrl
 * @description
 * # InstrumentForcedEditcontrollerCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('InstrumentForcedEditCtrl',
        function ($scope,
                  $controller,
                  $stateParams,
                  CommonService,
                  $state) {
            var self = this;
            self.key = 0;
            self.mandatoryInstrumentSet = [];

            self.init = function () {
                // 初始化scope的值，直接引会将出现多级联系
                self.initScope();
                $scope.isShowSaveAndNew = true;     // 当编辑强检器具时,隐藏保存并新建按钮
            };

            self.initScope = function () {
                // 获取传入的数据及键值
                self.mandatoryInstrumentSet = $stateParams.mandatoryInstrumentSet;
                self.key = $stateParams.key;

                // 初始化数据
                $scope.data = self.mandatoryInstrumentSet[self.key];
                $scope.instrumentFirstLevelType = $scope.data.instrumentType.instrumentFirstLevelType;
                $scope.discipline = $scope.data.instrumentType.instrumentFirstLevelType.discipline;
                $scope.generativeDepartment = $scope.data.generativeDepartment;

            };

            // 保存/更新
            self.submit = function () {
                self.mandatoryInstrumentSet[self.key] = $scope.data;
                CommonService.deleteCurrentState();
                $state.transitionTo('InstrumentForced.InstrumentForcedApply.addApply', {mandatoryInstrumentList: self.mandatoryInstrumentSet}, {reload: true});
            };

            self.init();
            $scope.submit = self.submit;
        });
