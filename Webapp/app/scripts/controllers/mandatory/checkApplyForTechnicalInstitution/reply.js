'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatoryCheckapplyfortechnicalinstitutionreplyCtrl
 * @description
 * # MandatoryCheckapplyfortechnicalinstitutionreplyCtrl
 * 检定申请（适用技术机构）回复
 */
angular.module('webappApp')
    .controller('MandatoryCheckApplyForTechnicalInstitutionReplyCtrl',
        function (CheckApplyForTechnicalInstitutionService,
                  checkApplyForInstrumentUserService,
                  $stateParams,
                  $scope,
                  UserServer,
                  CommonService,
                  WorkService) {
            var self = this;
            WorkService.initController(self, $scope);
            self.init = function () {
                self.load();

                UserServer.getCurrentLoginUser(function (currentUser) {
                    $scope.currentUser = currentUser;
                });
            };

            self.load = function () {
                var workId = $stateParams.workId;
                WorkService.getById(workId, function (work) {
                    $scope.work = work;
                });
            };

            /**
             * 提交数据
             * @return   {[type]}
             * @author 梦云智 http://www.mengyunzhi.com
             * @DateTime 2017-11-22T17:55:58+0800
             */
            self.submit = function () {
                CheckApplyForTechnicalInstitutionService.reply($scope.work.apply.id, $scope.work.apply, function () {
                    CommonService.success();
                });
            };

            self.init();
            $scope.submit = self.submit;
        });
