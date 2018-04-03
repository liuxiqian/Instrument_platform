'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatoryCheckapplyfortechnicalinstitutionIndexCtrl
 * @description
 * # MandatoryCheckapplyfortechnicalinstitutionIndexCtrl
 * 强检器具检定管理 综合查询（适用于技术机构）
 */
angular.module('webappApp')
    .controller('MandatoryCheckApplyForTechnicalInstitutionIndexCtrl',
        function (CheckApplyForTechnicalInstitutionService,
                  $scope,
                  CommonService,
                  config,
                  MandatoryCheckApplyService,
                  checkApplyForInstrumentUserService,
                  $stateParams) {
            var self = this;
            CommonService.initControllerPage(self, $scope);

            // 初始化
            self.init = function () {
                $scope.params = self.initScopeParams();
                self.load();

                $scope.showView = MandatoryCheckApplyService.showView;
                $scope.showReply = MandatoryCheckApplyService.showReply;
                $scope.downloadCheckApply = self.downloadCheckApply;
            };

            // 加载数据
            self.load = function () {
                CheckApplyForTechnicalInstitutionService.pageByParams(self.generateQueryParams(), function (pageData) {
                    $scope.data = pageData;
                });
            };

            // 初始化查询参数
            self.initScopeParams = function () {
                return {
                    page: $stateParams.page ? $stateParams.page : 0,
                    size: $stateParams.size ? $stateParams.size : config.size,
                    instrumentUserName: $stateParams.instrumentUserName,      // 器具用户名称
                    startApplyDate: $stateParams.startApplyDate,          // 申请开始日期
                    endApplyDate: $stateParams.endApplyDate           // 申请结束日期
                };
            };


            // 下载检定申请
            self.downloadCheckApply = function (checkApplyId) {
                // 调用Service中下载检定申请方法
                checkApplyForInstrumentUserService.downloadPdfByCheckApplyId(checkApplyId);
            };

            self.init();
        });
