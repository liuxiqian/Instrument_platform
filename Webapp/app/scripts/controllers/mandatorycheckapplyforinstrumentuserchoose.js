'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatorycheckapplyforinstrumentuserchooseCtrl
 * @description
 * 强检器具检定管理 -- 检定申请  -- 新增 -- 选择检定器具
 * # MandatorycheckapplyforinstrumentuserchooseCtrl
 * Controller of the webappApp
 * panjie
 */
angular.module('webappApp')
    .controller('MandatorycheckapplyforinstrumentuserchooseCtrl',
        function($scope, $stateParams, CommonService, $state, mandatoryInstrumentService) {
        var self = this;

        self.init = function() {
            self.mandatoryInstrumentSet = $stateParams.mandatoryInstrumentSet;
            mandatoryInstrumentService.initController(self, $scope);
            $scope.params = self.initScopeParams();
            self.load();
        };

        /**
         * 是否选中当前记录
         * 如果在前面的申请中，已经包含了当前实体了，则选中；否则，不选中
         * @param  强检器具  object
         * @return
         * panjie
         */
        self.isChecked = function(object) {
            var found = false;
            angular.forEach($stateParams.mandatoryInstrumentSet, function(value) {
                if (!found && (object.id === value.id)) {
                    object._checked = true;
                    found = true;
                }
            });

            return found;
        };

        // 申请检定
        self.applyForCheck = function() {
            // 将用户选择的器具列表，送入检定申请add路由
            $state.go('mandatory.checkApplyForInstrumentUser.Add', { mandatoryInstrumentSet: self.mandatoryInstrumentSet});
        };

        self.reload = self.load;

        /**
         * 将选中\反选的对象添加到数组中，或从数组中删除
         * @param
         * @author 梦云智 http://www.mengyunzhi.com
         * @DateTime 2017-12-11T19:36:56+0800
         */
        self.add = function(object) {
            // 如果有，就pop。没有，则push
            if (CommonService.popByKeyName(object, 'id', self.mandatoryInstrumentSet) === undefined) {
                self.mandatoryInstrumentSet.push(object);
            }
        };

        self.init();
        $scope.submit = self.submit;
        $scope.applyForCheck = self.applyForCheck;
        $scope.isChecked = self.isChecked;
        $scope.showCheckAll = true;
        $scope.add = self.add;
    });
