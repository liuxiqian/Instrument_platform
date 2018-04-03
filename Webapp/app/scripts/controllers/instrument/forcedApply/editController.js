'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:ManadatoryIntegratedEditCtrl
 * @description
 * # ManadatoryIntegratedEditCtrl
 * 强检器具档案 综合查询 编辑 panjie
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('InstrumentForcedApplyEditCtrl', ['$scope',
        'mandatoryInstrumentService',
        '$stateParams',
        'CommonService',
        '$controller',
        'departmentService',
        'MandatoryInstrumentApplyService',
        function($scope,
            mandatoryInstrumentService,
            $stateParams,
            CommonService,
            $controller,
            departmentService,
            MandatoryInstrumentApplyService) {
            var self = this;
            angular.extend(self, $controller('InstrumentForcedApplyAddCtrl', { $scope: $scope }));


            $scope.technicalInstitutions = []; // 技术机构
            $scope.checkTechnicalInstitution = {}; // 进行自动匹配是否有检定能力的技术机构=

            self.editInit = function() {
                $scope.step = 'step2';
                $scope.data.auditType = ''; //
                $scope.isAdd = false; // 审核类型
            };

            // 显示当前区域的所有技术机构
            self.getAllTechnicalInstitutionsByDistrictId = function(callback) {
                var districtId = $scope.data.work.auditDepartment.district.id;
                departmentService.getAllTechnicalInstitutionsByDistrictId(districtId, function(data) {
                    $scope.technicalInstitutions = data;
                    if ($scope.technicalInstitutions.length > 0) {
                        $scope.technicalInstitution = $scope.technicalInstitutions[0];
                    }
                    if (callback) {
                        callback();
                    }
                });
            };

            self.editInit();

            // 显示历史审核记录
            self.showHistoryOpinion = function() {
                if (!$scope.data.work.done && $scope.data.work.preWork) {
                    return true;
                } else {
                    return false;
                }
            };

            // 删除强检器具
            self.delete = function(data) {
                CommonService.warning(function(success, error) {
                    mandatoryInstrumentService.delete(data.id,
                        function(response) {
                            if (response.status === 204) {
                                var index = CommonService.searchByIndexName(data, 'id', $scope.data.work.apply.mandatoryInstruments);
                                $scope.data.work.apply.mandatoryInstruments.splice(index, 1);
                                success();
                            } else {
                                error(response);
                            }
                        });
                });
            };

            // 编辑强检器具
            self.edit = function(data) {
                $scope.data.mandatoryInstrument = data;
                $scope.data.mandatoryInstrument.instrumentFirstLevelType = data.instrumentType.instrumentFirstLevelType;
                $scope.discipline = data.instrumentType.instrumentFirstLevelType.discipline;
                $scope.step = 'step1';
            };


            self.downloadWord = function() {
                $scope.showDownloadWord = false;
                MandatoryInstrumentApplyService.downloadWordApplyById($scope.data.work.apply.id, function() {
                    $scope.showDownloadWord = true;
                });
            };


            // 状态过滤器(当显示编辑删除时，显示全部记录。否则，不显示退回的记录)
            self.statusFilter = function() {
                if ($scope.data.work.auditDepartment.id === $scope.data.work.apply.department.id) {
                    return {};
                } else {
                    return { status: '!' + 3 };
                }
            };

            // 禁用 保存工作 按钮
            self.saveWorkDisabled = function() {
                if ($scope.data.auditType === 'backToPreDepartment' ||
                    $scope.data.auditType === 'done' ||
                    $scope.data.auditType === 'backToApplyDepartment') {
                    return false;
                } else if ($scope.data.auditType === 'sendToNextDepartment') {
                    if ($scope.data.nextWork.department && $scope.data.nextWork.department.id) {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            };

            // 监视工作发生变化后，更新是否显示crud相关按钮
            $scope.$watch('data.work', self.showCrud);


            self.saveAndClose = function() {
                self.save(function() {
                    CommonService.success();
                });
            };

            self.showStep = function(step) {
                if ($scope.step === step) {
                    return true;
                } else {
                    return false;
                }
            };




            $scope.showAddButton = true;
            $scope.saveAndNew = self.saveAndNew;
            $scope.saveAndClose = self.saveAndClose;
            $scope.submit = self.submit;
            $scope.showCrud = true;
            $scope.back = CommonService.back;
            $scope.delete = self.delete;
            $scope.edit = self.edit;
            $scope.saveWorkDisabled = self.saveWorkDisabled;
            $scope.downloadWord = self.downloadWord;
            $scope.showDownloadWord = true;
            $scope.showBack = self.showBack;
            $scope.statusFilter = self.statusFilter;
            $scope.back = self.back;
            $scope.showDelete = self.showDelete;

        }
    ]);
