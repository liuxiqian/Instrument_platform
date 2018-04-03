'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatoryInstrumentIndexCtrl
 * @description
 * # 强制检定器具综合查询
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('MandatoryInstrumentIndexCtrl',
        ['$scope', '$stateParams', 'config', 'CommonService', 'mandatoryInstrumentService', '$state', 'UserServer',
            function ($scope, $stateParams, config, CommonService, mandatoryInstrumentService, $state, UserServer) {
                var self = this;
                CommonService.initControllerPage(self, $scope);

                //获取所有数据
                self.init = function () {
                    $scope.params = self.scopeParamsInit();
                    self.load();
                };

                self.load = function () {
                    var params = self.generateQueryParams();
                    mandatoryInstrumentService.pageAllOfCurrentManageDepartmentBySpecification(params, function (data) {
                        $scope.data = data;
                    });
                };

                // 获取ui-route传入的参考
                self.scopeParamsInit = function () {
                    return $stateParams;
                };


                // 提交数据
                self.submit = function () {
                    //查询条件，初始化数据
                    self.reload();
                };

                // 是否显示器具用户
                self.showQijuyonghu = function () {
                    return UserServer.showQijuyonghu();
                };

                self.generateQueryParams = function () {
                    // 整理传入的参数信息。我们之所以在params的属性中只使用了基本类型，是为了以后将查询条件放到url中做准备
                    var params = {
                        page: $scope.params.page,
                        size: $scope.params.size,
                        districtId: $scope.params.district.id,          // 区域
                        instrumentUserId: $scope.params.department.id,    // 器具用户(部门)
                        checkDepartmentId: $scope.params.checkDepartment.id,
                        id: $scope.params.id,                                    //
                        disciplineId: $scope.params.discipline.id,
                        instrumentTypeFirstLevelId: $scope.params.instrumentTypeFirstLevel.id,
                        instrumentTypeId: $scope.params.instrumentType.id,
                        audit: $scope.params.audit,
                        name: $scope.params.name,
                        overdue: $scope.params.overdue
                    };
                    return params;
                };

                self.detailIntegratedAudit = function (object) {
                    $stateParams.mandatoryInstrumentId = object.id;
                    $stateParams.districtId = self.params.district.id;
                    $stateParams.departmentId = self.params.department.id;
                    $stateParams.disciplineId = self.params.discipline.id;
                    $stateParams.checkDepartmentId = self.params.checkDepartment.id;
                    $stateParams.instrumentTypeFirstLevelId = self.params.instrumentTypeFirstLevel.id;
                    $stateParams.instrumentTypeId = self.params.instrumentType.id;
                    $stateParams.name = self.params.name;
                    $stateParams.overdue = self.params.overdue;

                    $state.go('mandatory.instrumentCheckInfoManage', $stateParams, {reload: true});
                };

                /**
                 * 按当前条件导出excel表
                 * @return   {[type]}                 [description]
                 * @author 梦云智 http://www.mengyunzhi.com
                 * @DateTime 2017-11-12T15:46:10+0800
                 */
                self.exportExcel = function () {
                    var params = self.generateQueryParams();
                    mandatoryInstrumentService.exportExcelOfCurrentManagementDepartment(params, function () {
                    });
                };

                // 执行获取数据
                self.init();
                // 方法统一暴露
                $scope.read = true;		//显示查看按钮
                $scope.showShi = UserServer.showShi;
                $scope.showQuxian = UserServer.showQuxian;
                $scope.submit = self.submit;
                $scope.console = console;
                $scope.detailIntegratedAudit = self.detailIntegratedAudit;
                $scope.exportExcel = self.exportExcel;
                $scope.showQijuyonghu = self.showQijuyonghu;
            }]);
