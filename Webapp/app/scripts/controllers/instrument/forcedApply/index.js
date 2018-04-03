'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:InstrumentForcedapplyIndexCtrl
 * @description 强检器具申请查看列表
 * # InstrumentForcedapplyIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('InstrumentForcedapplyIndexCtrl', function(
        $scope,
        MandatoryInstrumentWorkService,
        ApplyTypeService,
        UserServer,
        CommonService,
        config,
        WorkService) {

        var self = this;
        WorkService.initController(self, $scope);

        self.init = function() {
            // 获取当前登录用户，用于判断是否显示 新增 按钮
            UserServer.getCurrentLoginUser(function(user) {
                self.currentUser = user;
                // 进行查询操作时，是否显示器具用户查询条件
                self.getIsShowInstrumentUser();
            });

            $scope.params = self.initScopeParams();
            self.load();


            // 统一暴露
            $scope.showAddButton = self.showAddButton;
            $scope.isShowInstrumentUser = self.isShowInstrumentUser;
            $scope.reload = self.reload;
        };

        self.load = function() {
            // 获取后台数据
            MandatoryInstrumentWorkService.pageOfCurrentUser(self.generateQueryParams(), function(data) {
                $scope.data = data;
            });
        };

        // 进行查询操作时，是否显示器具用户查询条件
        self.getIsShowInstrumentUser = function () {
            if (self.currentUser.department.departmentType.pinyin === 'qijuyonghu') {
                self.isShowInstrumentUser = false;
            } else {
                self.isShowInstrumentUser = true;
            }
        };

        // 是否显示新增按钮
        self.showAddButton = function() {
            if (self.currentUser && self.currentUser.department.departmentType.name === '器具用户') {
                return true;
            } else {
                return false;
            }
        };


        self.init();

    });
