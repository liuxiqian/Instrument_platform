'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatoryOverduecheckapplyforinstrumentuserViewCtrl
 * @description
 * # MandatoryOverduecheckapplyforinstrumentuserViewCtrl
 * 强检器具超期检定管理 查看
 * panjie
 */
angular.module('webappApp')
    .controller('MandatoryOverduecheckapplyforinstrumentuserViewCtrl',
        function (CommonService, $scope, WorkService, $stateParams) {
            var self = this;

            self.init = function () {
                self.reload();
            };

            self.reload = self.load = function () {
                var workId = $stateParams.workId;
                if (!workId) {
                    CommonService.error("错误", "未接收到workId");
                }
                WorkService.getById(workId, function(work) {
                    $scope.work = work;
                });
            };

            self.init();

        });
