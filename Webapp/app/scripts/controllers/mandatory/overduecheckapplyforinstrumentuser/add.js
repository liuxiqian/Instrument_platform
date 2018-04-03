'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatoryOverduecheckapplyforinstrumentuserAddCtrl
 * @description
 * 继承于一般检定申请
 * # MandatoryOverduecheckapplyforinstrumentuserAddCtrl
 * 超级检定申请  - 新增
 * panjie
 */
angular.module('webappApp')
    .controller('MandatoryOverduecheckapplyforinstrumentuserAddCtrl',
        function($scope, $stateParams, OverdueCheckApplyService, $state) {
            var self = this;
            self.init = function() {
                $scope.data = {
                    reason: '',     // 超期未检理由
                    mandatoryInstrumentSet: $stateParams.mandatoryInstrumentSet // 强检器具
                };
            };

            self.submit = function() {
               OverdueCheckApplyService.save($scope.data, function() {
                    $state.go('InstrumentForcedTimeOutCheckApplyParent.InstrumentForcedTimeOutCheckApplyIndex', {}, {reload: true});
               });
            };

            self.init();
            $scope.submit = self.submit;
        });
