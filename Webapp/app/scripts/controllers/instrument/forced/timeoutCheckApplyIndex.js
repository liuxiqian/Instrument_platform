'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:InstrumentForcedTimeoutcheckapplyindexCtrl
 * @description
 * 强检器具超期检定备案管理 - 备案申请
 * # InstrumentForcedTimeoutcheckapplyindexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('InstrumentForcedTimeoutCheckApplyIndexCtrl',
        function ($scope,
                  $controller,
                  $stateParams,
                  $state,
                  config,
                  OverdueCheckApplyService,
                  CommonService,
                  UserServer,
                  WorkService) {

            var self = this;
            WorkService.initController(self, $scope);
            CommonService.initControllerPage(self, $scope);

            self.init = function () {
                // 获取当前登录用户
                UserServer.getCurrentLoginUser(function (user) {
                    self.currentUser = user;
                    // 进行查询操作时，是否显示器具用户查询条件
                    self.getIsShowInstrumentUser();
                    // 获取ui-route传入的参考
                    $scope.params = self.initScopeParams();

                    self.showApplyButton();
                    self.load();
                });
            };

            self.initScopeParams = function () {
                var params = {
                    page: $stateParams.page ? $stateParams.page : 0,
                    size: $stateParams.size ? $stateParams.size : config.size,
                    startApplyDate: $stateParams.startApplyDate,  // 申请起始日期
                    endApplyDate: $stateParams.endApplyDate,     // 申请结束日期
                    instrumentUserName: ''      // 器具用户名称
                };
                return params;
            };

            self.load = function () {
                OverdueCheckApplyService.pageOfCurrentUser(self.generateQueryParams())
                    .then(function success(response) {
                            $scope.data = response.data;
                        },
                        function error(response) {
                            CommonService.httpError(response);
                        });
            };

            // 进行查询操作时，是否显示器具用户查询条件
            self.getIsShowInstrumentUser = function () {
                if (self.currentUser.department.departmentType.pinyin === 'qijuyonghu') {
                    $scope.isShowInstrumentUser = false;
                } else {
                    $scope.isShowInstrumentUser = true;
                }
            };

            /**
             * 触发是否显示添加按钮
             * @return   {}
             * @author 梦云智 http://www.mengyunzhi.com
             * @DateTime 2017-12-17T10:08:04+0800
             */
            self.showApplyButton = function () {
                $scope.showApplyButton = false;
                UserServer.getCurrentLoginUser(function (user) {
                    if (user && user.department && user.department.departmentType && (user.department.departmentType.pinyin === 'qijuyonghu')) {
                        $scope.showApplyButton = true;
                    }
                });
            };

            self.init();
        });
