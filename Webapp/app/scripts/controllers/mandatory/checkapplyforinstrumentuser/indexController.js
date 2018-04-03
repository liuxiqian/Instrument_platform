'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatoryCheckApplyForInstrumentUserIndexCtrl
 * @description
 * # MandatoryCheckApplyForInstrumentUserIndexCtrl
 * Controller of the webappApp
 * 强检器具检定管理-检定申请
 */
angular.module('webappApp')
    .controller('MandatoryCheckApplyForInstrumentUserIndexCtrl',
        function ($stateParams,
                  checkApplyForInstrumentUserService,
                  CommonService,
                  $scope,
                  config,
                  ApplyService,
                  WorkService,
                  UserServer) {

            var self = this;

            self.initScopeParams = function () {
                return {
                    page: 0,
                    size: config.size,
                    sort: 'id,desc'
                };
            };

            // 定义获取数据的方法
            self.init = function () {
                $scope.params = this.initScopeParams();
                CommonService.initControllerPage(self, $scope);
                UserServer.getCurrentLoginUser(function(user){
                    self.currentUser = user;
                    self.reload();

                    // 将下载检定申请方法传到V层
                    $scope.downloadCheckApply = self.downloadCheckApply;
                    $scope.showDownloadCheckApply = self.showDownloadCheckApply;
                });
            };

            self.reload = self.load = function () {
                // 获取后台数据
                checkApplyForInstrumentUserService.pageByParams($scope.params, function (data) {
                    $scope.data = data;
                });
            };


            // 下载检定申请
            self.downloadCheckApply = function (checkApplyId) {
                // 调用Service中下载检定申请方法
                checkApplyForInstrumentUserService.downloadPdfByCheckApplyId(checkApplyId);
            };

            // 是否显示下载申请按钮
            self.showDownloadCheckApply = function(work) {
                if (work.apply.done) {
                    return true;
                } else {
                    return false;
                }
            };


            // 执行获取数据
            self.init();
        }
    );
