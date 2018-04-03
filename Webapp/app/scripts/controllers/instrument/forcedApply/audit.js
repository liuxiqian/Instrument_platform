'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:InstrumentForcedapplyAuditCtrl
 * @description
 * # InstrumentForcedapplyAuditCtrl
 * 强检器具备案申请 -- 办理
 */
angular.module('webappApp')
    .controller('InstrumentForcedapplyAuditCtrl',
        function ($scope, $stateParams, WorkService, MandatoryInstrumentApplyService, $uibModal, CommonService, mandatoryInstrumentService) {
            var self = this;
            self.init = function () {

                self.load(function () {
                    $scope.isWorkDone = self.isWorkDone();
                });
                $scope.showAutoMatch = $scope.showCheckAll = true;
                $scope.batchBack = self.batchBack;
                $scope.batchPass = self.batchPass;
                $scope.submit = self.submit;
                $scope.disabledBatchOperation = self.disabledBatchOperation;
            };

            self.load = function (callback) {
                var workId = $stateParams.workId;
                WorkService.getById(workId, function (work) {
                    $scope.work = work;
                    if (callback) {callback();}
                });
            };

            self.submit = function () {
                MandatoryInstrumentApplyService.reply($scope.work.apply, function () {
                    CommonService.success(undefined, undefined, function () {
                        CommonService.back(true);
                    });
                });
            };

            // 工作是否完成
            self.isWorkDone = function() {
                var done = true;
                angular.forEach($scope.work.apply.mandatoryInstruments, function(mandatoryInstrument){
                    if (done && (mandatoryInstrument.status === mandatoryInstrumentService.statues.NEW.value)) {
                        done = false;
                    }
                });
                return done;
            };

            // 批量通过
            self.batchPass = function (mandatoryInstruments) {
                self.beforeForAssginAndPassAndBack(mandatoryInstruments, function (checkedMandatoryInstruments, success, error) {
                    MandatoryInstrumentApplyService.batchPassByMandatoryInstrumentsAndApplyId(checkedMandatoryInstruments, $scope.work.apply.id, function (status) {
                        if (status === 204) {
                            success(undefined, undefined, undefined, function () {
                                self.init();
                            });
                        } else {
                            error();
                        }
                    });
                });
            };

            /**
             * 进行指定技术机构，批量通过，批量退回前的准备工作
             * @param  {array}   mandatoryInstruments 强检器具
             * @param  {Function} callback
             * @return {}
             * @author panjie
             */
            self.beforeForAssginAndPassAndBack = function (mandatoryInstruments, callback) {
                CommonService.warning(function (success, error) {
                    var checkedMandatoryInstruments = CommonService.getCheckedElementsByListsAndKey(mandatoryInstruments);
                    callback(checkedMandatoryInstruments, success, error);
                }, '确认此操作？');
            };

            // 批量退回
            self.batchBack = function (mandatoryInstruments) {
                // 提示用户输入退回理由
                $uibModal.open({
                    templateUrl: 'views/instrument/forcedApply/backedReasonModal.html',
                    controller: 'BackedReasonModalCtrl',
                    resolve: {
                        mandatoryInstruments: function () {
                            return mandatoryInstruments;
                        }
                    }
                });

            };

            /**
             * 是否禁用批量操作
             * 当一个强检器具也未选中的时，禁用批量操作
             * @return {boolean}
             */
            self.disabledBatchOperation = function () {
                console.log('one');
                if ($scope.work && $scope.work.apply && $scope.work.apply.mandatoryInstruments) {
                    var mandatoryInstruments = CommonService.getCheckedElementsByListsAndKey($scope.work.apply.mandatoryInstruments);
                    var count = 0;
                    angular.forEach(mandatoryInstruments, function (value) {
                        if (!count && value._checked) {
                            count++;
                        }
                    });

                    if (count > 0) {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }

            };

            self.init();
        });
// 批量退回弹窗
angular.module('webappApp')
    .controller('BackedReasonModalCtrl', function BackedReasonModalCtrl($scope, $uibModalInstance, $controller, $state, backedReasonService, MandatoryInstrumentApplyService, mandatoryInstruments) {
        var self = this;
        // 继承
        angular.extend(self, $controller('InstrumentForcedapplyAuditCtrl', {$scope: $scope}));

        // 获取最近10条退回理由
        self.getTop10ManagementDepartmentBackedReasons = function () {
            backedReasonService.getTop10ManagementDepartmentBackedReasons(function (data) {
                $scope.top10ManagementDepartmentBackedReasons = data;
            });
        };

        // 用户选中某一条理由
        self.check = function (backedReason) {
            $scope.reason = backedReason.reason;
        };

        self.ok = function () {
            $uibModalInstance.close();
            // 进行批量退回前的准备工作
            self.beforeForAssginAndPassAndBack(mandatoryInstruments, function (checkedMandatoryInstruments, success, error) {
                MandatoryInstrumentApplyService.batchBackByMandatoryInstrumentsAndReasonAndApplyId(checkedMandatoryInstruments, $scope.reason, $scope.work.apply.id, function (status) {
                    if (status === 204) {
                        success(undefined, undefined, undefined, function () {
                            self.init();
                            // 由于继承，不能正确的初始化。为了及时更新器具状态，进行刷新界面
                            $state.reload();
                        });
                    } else {
                        error();
                    }
                });
            });
        };

        self.cancel = function () {
            $uibModalInstance.close();
        };

        // 统一暴露
        $scope.ok = self.ok;
        $scope.cancel = self.cancel;
        $scope.check = self.check;

        self.getTop10ManagementDepartmentBackedReasons();
    });
