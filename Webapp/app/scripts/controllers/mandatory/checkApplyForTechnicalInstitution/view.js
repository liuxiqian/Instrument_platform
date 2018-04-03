'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatoryCheckapplyfortechnicalinstitutionViewCtrl
 * @description
 * # MandatoryCheckapplyfortechnicalinstitutionViewCtrl
 * 强制器具 检定申请 查看 （适用于技术机构）
 */
angular.module('webappApp')
    .controller('MandatoryCheckApplyForTechnicalInstitutionViewCtrl',
        function ($stateParams, $scope, CheckApplyForTechnicalInstitutionService, UserServer, CommonService, WorkService) {
            var self = this;
            $scope.init = self.init = function () {
                self.load(function () {
                    UserServer.getCurrentLoginUser(function (currentUser) {
                        $scope.currentUser = currentUser;
                        // 是否显示"设置检定信息"按钮
                        $scope.isShowInstrumentCheckInfo = self.isShowInstrumentCheckInfo();
                    });

                    $scope.submit = self.submit;


                });
            };

            self.load = function (callback) {
                var workId = $stateParams.workId;
                WorkService.getById(workId, function (work) {
                    $scope.work = work;
                    if (callback) {
                        callback();
                    }
                });
            };

            // 是否显示设置检定信息
            self.isShowInstrumentCheckInfo = function() {
                if ($scope.currentUser.department.departmentType.pinyin === 'qijuyonghu') {
                    // 当前用户属于器具用户
                    if ($scope.work.apply.auditingDepartment.departmentType.pinyin === 'guanlibumen') {
                        // 当前申请的审核人为管理部门，说明为域外申请
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            };

            /**
             * 提交数据
             * @return   {[type]}
             * @author 梦云智 http://www.mengyunzhi.com
             * @DateTime 2017-11-22T17:55:58+0800
             */
            self.submit = function () {
                CheckApplyForTechnicalInstitutionService.reply($scope.data.id, $scope.data, function () {
                    CommonService.success();
                });
            };

            self.init();

            // 判断当前用户是否可以设置器具检定信息
            $scope.$watch('data', function (newValue) {
                // 判断$scope.data是否被赋值
                if (typeof newValue !== 'undefined' && newValue.id) {
                    // 判断检定申请的检定部门是否为市级管理部门,当前用户是否为器具用户
                    if (newValue.auditingDepartment.departmentType.name === '管理部门' &&
                        newValue.auditingDepartment.district.districtType.name === '市' &&
                        newValue.department.departmentType.name === '器具用户' && newValue.plannedCheckDate !== null) {
                        $scope.isShowInstrumentCheckInfo = true;
                    }
                }
            }, true);
        });
