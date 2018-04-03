'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatoryOverduecheckapplyforinstrumentuserChooseCtrl
 * @description
 * # MandatoryOverduecheckapplyforinstrumentuserChooseCtrl
 * 强检器具 超期检定申请（器具用户） 选择器具
 */
angular.module('webappApp')
    .controller('MandatoryOverduecheckapplyforinstrumentuserChooseCtrl',
        function ($scope, $state, $stateParams, CommonService, mandatoryInstrumentService) {
            var self = this;
            mandatoryInstrumentService.initController(self, $scope);
            self.init = function () {
                self.mandatoryInstrumentSet = $stateParams.mandatoryInstrumentSet;
                $scope.params = self.initScopeParams();

                self.load();

            };

            // 加载数据
            self.reload = self.load = function () {
                mandatoryInstrumentService.pageAllOfCurrentUserBySpecification(self.generateQueryParamsOfSort(), function (data) {
                    $scope.data = data;
                });
            };

            self.generateQueryParamsOfSort = function() {
                var params = self.generateQueryParams();
                params.sort = 'lastCheckDate,desc';     // 按申请截至日期排序
                return params;
            };

            // 申请检定
            self.applyForCheck = function () {
                // 将用户选择的器具列表，送入检定申请add路由
                $state.go('InstrumentForcedTimeOutCheckApplyParent.InstrumentForcedTimeOutCheckApplyIndex.Add', {mandatoryInstrumentSet: self.mandatoryInstrumentSet});
            };

            $scope.applyForCheck = self.applyForCheck;
            $scope.isChecked = self.isChecked;
            $scope.showCheckAll = true;
            $scope.add = self.add;

            self.init();
        });
