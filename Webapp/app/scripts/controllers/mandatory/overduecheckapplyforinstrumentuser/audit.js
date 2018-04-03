'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatoryOverduecheckapplyforinstrumentuserAuditCtrl
 * @description
 * # MandatoryOverduecheckapplyforinstrumentuserAuditCtrl
 * 超期检定申请 审核（适用于管理部门)
 * 继承于查看
 * panjie
 */
angular.module('webappApp')
    .controller('MandatoryOverduecheckapplyforinstrumentuserAuditCtrl',
        function($scope, OverdueCheckApplyService, CommonService, $stateParams, WorkService) {
            var self = this;

            self.init = function() {
                self.load();
                $scope.agree = self.agree;
                $scope.submit = self.submit;
            };

            self.load = self.reload = function() {
                var workId = $stateParams.workId;
                if (!workId) {
                    CommonService.error("错误", "未接收到workId");
                }
                WorkService.getById(workId, function(work) {
                    $scope.work = work;
                });
            };

            // 点击同意，或不同意
            self.agree = function(agree) {
                $scope.work.apply.agree = agree;
            };

            // 提交数据
            self.submit = function() {
                OverdueCheckApplyService.reply($scope.work, function() {
                    CommonService.success(undefined, undefined, function(){
                        CommonService.back(true);
                    });
                });
            };

            // 如果使用了$scope指明了继承关系，则不进行初始化操作
            self.init();
        });
