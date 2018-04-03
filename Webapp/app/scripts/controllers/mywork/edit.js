'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MyworkEditCtrl
 * @description
 * # MyworkEditCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('MyworkEditCtrl', function($scope,
        WorkService,
        $stateParams,
        ApplyService,
        MandatoryInstrumentWorkService,
        CommonService,
        UserServer,
        mandatoryInstrumentService) {

        var self = this;

        self.workId = $stateParams.id;
        self.initScope = function() {
            if (self.work.done === true) {
                CommonService.error('当前工作已审核', '请勿重复审核同一工作', function() {
                    CommonService.back();
                });
            } else {
                $scope.data = {};
                $scope.data.currentWorkflowNode = self.work.workflowNode; // 当前工作流结点
                $scope.data.nextWork = {}; // 下一工作
                $scope.data.nextWork.nextWorkflowNode = {}; // 选择的下一工作流结点
                $scope.data.work = self.work;

                // 将工作设置为在办
                if (typeof($scope.data.work.done) !== 'undefined' && !$scope.data.work.done) {
                    WorkService.updateToDoingByWork($scope.data.work);
                }
            }
        };

        //  初始化
        self.init = function(callback) {
            WorkService.getById(self.workId, function(work) {
                self.work = work;
                self.initScope();
                if (callback) { callback(); }
            });
        };

        // 更新申请信息
        self.updateApply = function() {
            console.log('更新工作及申请的状态');
            var work = $scope.data.work;
            work.auditType = $scope.data.auditType;
            work.nextWork = $scope.data.nextWork;
            console.log('调用审核');
            WorkService.audit(work, function() {
                CommonService.success('操作成功');
            });
        };


        // 指定检定技术机构
        self.assignToTechnicalInstitution = function() {
            var assignMandatoryInstruments = [];
            var departmentId = $scope.technicalInstitution.id;

            // 把已经指定的器具选出来，然后送后台进行自动审核
            angular.forEach($scope.data.work.apply.mandatoryInstruments, function(value) {
                if (value.assgin === true && value.status === -1) {
                    assignMandatoryInstruments.push(value);
                }
            });

            // 更新所选器具的指定检定技术机构
            mandatoryInstrumentService.updateCheckDepartmentOfMandatoryInstrumentsByDepartmentId(assignMandatoryInstruments, departmentId, function() {
                // 更新器具对审核信息
                angular.forEach(assignMandatoryInstruments, function(value) {
                    value.audit = true;
                    value.checkDepartment = $scope.technicalInstitution;
                    value.status = 0;
                });
            });
        };


        self.getExtendViewDirectoryByAction = function(action) {
            if ($scope.data && $scope.data.work) {
                return 'views/' + $scope.data.work.apply.applyType.viewDirectory + action + 'Index.html';
            }
        };

        self.getExtendViewDirectory = function() {
            return self.getExtendViewDirectoryByAction('edit');
        };

        if (!$scope._extend) {
            self.init();
        }

        // 判断是否显示增加器具按钮
        $scope.$watch('data.work', function(newValue) {
            if (newValue && newValue.preWork === null && newValue.done !== true) {
                $scope.showAdd = true;
            } else {
                $scope.showAdd = false;
            }
        }, true);

        $scope.updateApply = self.updateApply; // 保存申请
        $scope.showCrud = true;
        $scope.showAutoMatch = self.showAutoMatch;
        $scope.showHistoryOpinion = self.showHistoryOpinion;
        $scope.assignToTechnicalInstitution = self.assignToTechnicalInstitution;
        $scope.showSubmit = true;
        $scope.getExtendViewDirectory = self.getExtendViewDirectory;

    });
