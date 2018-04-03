'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:InstrumentForcedAddworkCtrl
 * @description
 * # panie
 * 强检器具备案 添加器具后 显示工作信息
 */
angular.module('webappApp')
    .controller('InstrumentForcedAddapplyCtrl',
        function ($scope, $stateParams, UserServer, MandatoryInstrumentApplyService, CommonService) {
        var self = this;

        self.init = function () {
            $scope.work = self.initScopeData();

            UserServer.getCurrentLoginUser(function(currentUser) {
               $scope.currentUser = currentUser;
            });

            $scope.showAdd = true;
            $scope.submit = self.submit;
            $scope.delete = self.delete;
        };

        self.initScopeData = self.initData = function () {
            return {
                apply: {
                    mandatoryInstruments: $stateParams.mandatoryInstrumentList
                }
            };
        };

        self.delete = function(object) {
            angular.forEach($scope.work.apply.mandatoryInstruments, function(mandatoryInstrument, key){
                if (object === mandatoryInstrument) {
                    $scope.work.apply.mandatoryInstruments.splice(key, 1);
                }
            });
        };

        self.submit = function() {
            MandatoryInstrumentApplyService.save($scope.work.apply, function() {
                CommonService.success();
            });
        };

        self.init();
    });
