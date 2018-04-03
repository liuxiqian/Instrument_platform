'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:InstrumentForcedapplyEditindexCtrl
 * @description
 * # InstrumentForcedapplyEditindexCtrl
 * Controller of the webappApp
 * @Author panjie
 */
angular.module('webappApp')
    .controller('InstrumentForcedApplyEditindexCtrl',
        function($scope, $controller, $stateParams, WorkService, MandatoryInstrumentApplyService, instrumentStatus) {
            var self = this;

            $scope.init = function(callback) {
                var workId = $stateParams.id;
                $scope.showCrud = true; // 显示增册改查
                $scope.showAutoMatch = false; // 显示自动批配
                $scope.showCheckAll = false; // 显示全选
                // 先获取工作对应的申请，再获取申请对应的强检器具
                WorkService.getById(workId, function(work) {
                    MandatoryInstrumentApplyService.findById(work.apply.id, function(data) {
                        $scope.work.mandatoryInstrumentApply = data;
                        if (callback) { callback(); }
                    });
                });
            };

            // 是否显示自动匹配

            angular.extend(self, $controller('InstrumentForcedApplyAuditIndexCtrl', { $scope: $scope }));

            /**
             * 是否显示删除按钮
             * @param object
             * @returns {boolean}
             */
            self.showDelete = function(object) {
                if (object.status === instrumentStatus.new) {
                   return true;
                } else {
                    return false;
                }
            };

            $scope.showDelete = self.showDelete;


        });
