'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:InstrumentForcedTechnologydepartmentintegratedqueryindexctrlCtrl
 * @description 强检器具备案 综合查询（适用于技术机构)
 * # InstrumentForcedTechnologydepartmentintegratedqueryindexctrlCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('InstrumentForcedTechnologyDepartmentIntegratedQueryIndexCtrl', function($controller, $scope, $uibModal, mandatoryInstrumentService, CommonService, $stateParams) {
        var self = this;
        $stateParams.isConfirmedByTechnologyDepartment = 0;
        angular.extend(self, $controller('MandatoryAppointcheckinstrumentIndexCtrl', {$scope: $scope}));

        // 技术机构点击批量退回
        self.batchBack = function () {
            // 获取选中的器具
            var mandatoryInstruments = CommonService.getCheckedElementsByListsAndKey($scope.data.content);
            // 提示用户输入退回理由
            $uibModal.open({
                templateUrl: 'views/instrument/forced/TechnicalInstitutionBackedReasonModal.html',
                controller: 'TechnicalInstitutionBackedReasonModalCtrl',
                resolve: {
                    mandatoryInstruments: function() {
                        return mandatoryInstruments;
                    }
                }
            });
        };

        /**
         * 技术机构在指定的日期内，可以批量退回指定由其检定的器具
         * @param mandatoryInstruments
         * @author chuhang
         */
        self.batchBackAssignedInstrumentByMandatoryInstrumentSetAndReason = function (mandatoryInstruments, reason) {
            CommonService.warning(function(success, error) {
                mandatoryInstrumentService.batchBackAssignedInstrumentByMandatoryInstrumentSetAndReason(mandatoryInstruments, reason, function (status) {
                    if (status === 202) {
                        success();
                    } else {
                        error();
                    }
                });
            });
        };

        /**
         * 技术机构在指定的日期内，可以批量确认指定由其检定的器具
         * @param mandatoryInstruments
         * @author chuhang
         */
        self.batchConfirmAssignedInstrumentByMandatoryInstrumentSetOfCurrentUser = function (mandatoryInstruments) {
            CommonService.warning(function(success, error) {
                mandatoryInstrumentService.batchConfirmAssignedInstrumentByMandatoryInstrumentSetOfCurrentUser(mandatoryInstruments, function (status) {
                    if (status === 202) {
                        success();
                    } else {
                        error();
                    }
                });
            });
        };

        // 批量确认
        self.batchConfirm = function () {
            // 获取选中的器具
            var mandatoryInstruments = CommonService.getCheckedElementsByListsAndKey($scope.data.content);
            // 批量确认指定由其检定的器具
            self.batchConfirmAssignedInstrumentByMandatoryInstrumentSetOfCurrentUser(mandatoryInstruments);
        };

        // 统一暴露
        $scope.batchBackAssignedInstrumentByMandatoryInstrumentSetAndReason = self.batchBackAssignedInstrumentByMandatoryInstrumentSetAndReason;
        $scope.batchConfirmAssignedInstrumentByMandatoryInstrumentSetOfCurrentUser = self.batchConfirmAssignedInstrumentByMandatoryInstrumentSetOfCurrentUser;
        $scope.batchBack = self.batchBack;
        $scope.batchConfirm = self.batchConfirm;
        
        console.log($scope.params);
    });

// 技术机构批量退回弹窗
angular.module('webappApp')
    .controller('TechnicalInstitutionBackedReasonModalCtrl', function BackedReasonModalCtrl ($scope, $uibModalInstance, $controller, $state, backedReasonService, MandatoryInstrumentApplyService, mandatoryInstruments) {
        var self = this;
        // 继承
        angular.extend(self, $controller('InstrumentForcedTechnologyDepartmentIntegratedQueryIndexCtrl', { $scope: $scope }));

        // 获取最近10条退回理由
        self.getTop10TechnicalInstitutionBackedReasons = function () {
            backedReasonService.getTop10TechnicalInstitutionBackedReasons(function (data) {
                $scope.top10TechnicalInstitutionBackedReasons = data;
            });
        };

        // 用户选中某一条理由
        self.check = function (technicalInstitutionBackedReason) {
            $scope.reason = technicalInstitutionBackedReason.reason;
        };

        self.ok = function () {
            $uibModalInstance.close();
            // 批量退回指定由其检定的器具
            self.batchBackAssignedInstrumentByMandatoryInstrumentSetAndReason(mandatoryInstruments, $scope.reason);
        };

        self.cancel = function () {
            $uibModalInstance.close();
        };

        // 统一暴露
        $scope.ok = self.ok;
        $scope.cancel = self.cancel;
        $scope.check = self.check;

        // 获取最近10条技术机构退回理由
        self.getTop10TechnicalInstitutionBackedReasons();
    });
