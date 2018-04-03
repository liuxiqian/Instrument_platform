'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatorycalibraterecordauditCtrl
 * @description
 * # MandatorycalibraterecordauditCtrl
 * 强制检定审核
 */
angular.module('webappApp')
    .controller('InstrumentForcedApplyAuditIndexCtrl', function ($scope,
                                                                 $stateParams,
                                                                 $uibModal,
                                                                 WorkService,
                                                                 MandatoryInstrumentWorkService,
                                                                 CommonService,
                                                                 MandatoryInstrumentApplyService,
                                                                 UserServer,
                                                                 $timeout) {
        var self = this;

        //定义变量
        self.allMandatoryInstruments = [];       // 显示所有的强检器具
        self.instrumentsNotTechnicalInstitutionsAndStatusNormal = [];       // 显示没有被指定过且状态正常的强检器具
        // 初始化
        self.init = function () {
            if (typeof($scope.init) !== 'undefined') {
                $scope.init(function () {
                    self.initPublicScope();
                });
            } else {
                $scope.$watch('work', function (newValue) {
                    if (newValue && newValue.id) {
                        self.getMandatoryInstrumentApply(function () {
                            $scope.batchPass = self.batchPass;
                            $scope.batchBack = self.batchBack;
                            $scope.showCheckAll = true; // 显示全选
                            $scope.disabledBatchOperation = self.disabledBatchOperation;
                            $scope.assignToTechnicalInstitution = self.assignToTechnicalInstitution;
                            $scope.showAutoMatch = self.showAutoMatch();
                            $scope.showCrud = self.showCrud();
                            $scope.showOrHide = true;
                            $scope.showMessage = self.hideMessage;
                            self.initPublicScope();
                        });
                    }
                });
            }
        };

        /**
         * 显示生成备案申请按钮
         * 当前登录部门为在办部门时或者当前工作状态为done（办结）时显示。
         * @return {}
         * panjie
         */
        self.watchWork = function (work) {
            $scope.showDownloadWord = false;                //默认不显示生成备案申请按钮

            // UserServer.getCurrentLoginUser(function(currentLoginUser) {
            //     if (currentLoginUser.department.id === work.auditDepartment.id) {
            //         $scope.showDownloadWord = true;     //当前登录部门为在办部门显示生成备案申请按钮
            //     }
            // });

            // if (work && work.apply && work.apply.done) {
            //     $scope.showDownloadWord = true;         //前工作状态为done（办结）时显示
            // }

            UserServer.getCurrentLoginUser(function (currentLoginUser) {
                if (work.apply.done && currentLoginUser.department.id === work.apply.department.id) {
                    $scope.showDownloadWord = true;        // 办结并且当前用户部门为申请部门时显示生成备案申请按钮
                }
            });
        };

        /**
         * 生成备案申请并下载
         * @return {}
         * panjie
         */
        self.downloadWord = function () {
            $scope.disabledDownloadWord = true;
            $scope.downloadWordText = '正在生成文档...';
            MandatoryInstrumentApplyService.downloadPdfReportByApplyId($scope.data.work.apply.id, function () {
                $timeout(function () {
                    $scope.downloadWordText = '生成备案申请';
                    $scope.disabledDownloadWord = false;
                }, 1500);
            });
        };



        // 初始化公共的scope信息（子对象可用）
        self.initPublicScope = function () {
            $scope.submit = self.submit;
            $scope.console = console;
        };


        // 数据准备好后进行初始化（由于work是传入的）
        self.getMandatoryInstrumentApply = function (callback) {
            $scope.data.work = $scope.work;
            UserServer.getCurrentLoginUser(function (user) {
                // 当前用户为管理部门，则触发自动审核
                if (user.department.departmentType.pinyin === 'guanlibumen') {
                    MandatoryInstrumentApplyService
                        .getMandatoryInstrumentApplyWithTechnicalInstitutionsAbilityByMandatoryInstrumentApplyId(
                            $scope.data.work.apply.id,
                            function (mandatoryInstrumentApply) {
                                $scope.work.mandatoryInstrumentApply = mandatoryInstrumentApply;
                                if (user.department.district.districtType.pinyin === 'shi') {
                                    $scope.work.mandatoryInstrumentApply.mandatoryInstruments = self.distinguishInstrumentsNotTechnicalAndStatusNormal($scope.work.mandatoryInstrumentApply.mandatoryInstruments);
                                } else {
                                    $scope.work.mandatoryInstrumentApply.mandatoryInstruments = self.distinguishAllMandatoryInstruments($scope.work.mandatoryInstrumentApply.mandatoryInstruments);
                                }
                                callback();
                            });
                }
            });
        };

        // 获取全部的器具信息
        self.distinguishAllMandatoryInstruments = function (mandatoryInstruments) {
            // 清空数据，避免缓存
            self.allMandatoryInstruments = [];  // 显示所有的强检器具
            self.allMandatoryInstruments = mandatoryInstruments;
            return self.allMandatoryInstruments;
        };

        // 获取未指定鉴定机构，且状态正常的器具信息
        self.distinguishInstrumentsNotTechnicalAndStatusNormal = function (mandatoryInstruments) {
            // 清空数据，避免缓存
            self.instrumentsNotTechnicalInstitutionsAndStatusNormal = [];  // 显示没有被指定过且状态正常的强检器具

            // 获取没有被指定过且状态正常的强检器具
            angular.forEach(mandatoryInstruments, function (value) {
                if (value.checkDepartment === null && value.status === 0) {
                    self.instrumentsNotTechnicalInstitutionsAndStatusNormal.push(value);
                }
            });

            return self.instrumentsNotTechnicalInstitutionsAndStatusNormal;
        };

        // 提交数据
        self.submit = function () {
            var workId = $scope.work.id;
            var data = $scope.data;
            MandatoryInstrumentWorkService.auditByIdOfCurrentUser(workId, data, function () {
                CommonService.success();
            });
        };

        // 是否显示CRUD按钮
        self.showCrud = function () {
            return false;
        };

        // 显示自动匹配
        self.showAutoMatch = function () {
            // 当前工作未完成，而且当前工作的审核部门为管理部门
            if (!$scope.data.work.done && $scope.data.work.auditDepartment.departmentType.name === '管理部门') {
                return true;
            } else {
                return false;
            }
        };

        // 指定技术机构
        self.assignToTechnicalInstitution = function (mandatoryInstruments) {
            self.beforeForAssginAndPassAndBack(mandatoryInstruments, function (checkedMandatoryInstruments, success, error) {
                MandatoryInstrumentApplyService.assignToTechnicalInstitutionByMandatoryInstruments(checkedMandatoryInstruments, function (status) {
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
         * 检定强检器具是否全部为新增器具
         * @param  {array}  强检器具数组
         * @return {boolean}
         * @author panjie
         */
        self.checkMandatoryInstrumentStatusIsAllNew = function (mandatoryInstruments) {
            var result = true;
            angular.forEach(mandatoryInstruments, function (value) {
                console.log(value);
                if (result && (value.status !== -1)) {
                    result = false;
                }
            });
            return result;
        };



        self.init();
        //暴露数据
        $scope.changeShowOrHide = self.changeShowOrHide;
        $scope.downloadWord = self.downloadWord;
        $scope.downloadWordText = '生成备案申请';
        $scope.$watch('work', self.watchWork);
    });



