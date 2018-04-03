'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatoryAppointcheckinstrumentIndexCtrl
 * @description
 * # 指定检定强检器具管理 使用对象： 技术机构
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('MandatoryAppointcheckinstrumentIndexCtrl', ['$scope', '$stateParams', 'config', 'InstrumentEmploymentInfoService', 'mandatoryInstrumentService', 'CommonService', 'UserServer', '$state', function ($scope, $stateParams, config, InstrumentEmploymentInfoService, mandatoryInstrumentService, CommonService, UserServer, $state) {
        var self = this;
        // 获取ui-route传入的参考
        CommonService.initControllerPage(self, $scope);

        // 获取查询参数
        self.generateQueryParams = function () {
            // 整理传入的参数信息。我们之所以在params的属性中只使用了基本类型，是为了以后将查询条件放到url中做准备
            var params = {
                page: $scope.params.page,
                size: $scope.params.size,
                districtId: $scope.params.district.id,          // 区域
                departmentId: $scope.params.department.id,    // 器具用户(部门)
                id: $scope.params.id,
                disciplineId: $scope.params.discipline.id,
                instrumentTypeFirstLevelId: $scope.params.instrumentTypeFirstLevel.id,
                instrumentTypeId: $scope.params.instrumentType.id,
                name: $scope.params.name,
                isConfirmedByTechnologyDepartment: $scope.params.isConfirmedByTechnologyDepartment       // 是否已被技术机构确认
            };

            return params;
        };

        //获取所有数据
        self.init = function () {
            // 分页数据初始化
            $scope.params = self.initScopeParams();
            self.load();
        };

        self.load = function() {
            var params = self.generateQueryParams();
            mandatoryInstrumentService.pageAllOfCurrentTechnicalInstitutionBySpecification(params, function (data) {
                $scope.data = data;
            });
        };

        // 提交数据
        self.submit = function () {
            //提交查询数据后重新初始化内容
            self.reload();
        };

        self.detailIntegratedAudit = function (mandatoryInstrument) {
            $stateParams.mandatoryInstrumentId = mandatoryInstrument.id;
            $stateParams.districtId = self.params.district.id;
            $stateParams.disciplineId = self.params.discipline.id;
            $stateParams.instrumentTypeFirstLevelId = self.params.instrumentTypeFirstLevel.id;
            $stateParams.instrumentTypeId = self.params.instrumentType.id;
            $stateParams.departmentId = self.params.department.id;
            $stateParams.name = self.params.name;
            $stateParams.isConfirmedByTechnologyDepartment = self.params.isConfirmedByTechnologyDepartment;

            $state.go('mandatory.instrumentCheckInfo', $stateParams, {reload: true});
        };

        // 隐藏检定机构字段
        self.hideCheckDepartment = function () {
            return true;
        };

        /**
         * 为当前的技术机构导出excel
         * @author chuhang
         */
        self.exportExcel = function () {
            var params = self.generateQueryParams();
            mandatoryInstrumentService.exportExcelOfCurrentTechnicalInstitution(params, function () {
            });
        };

        self.init();
        // 方法统一暴露
        $scope.isShow = {operation: false, appoint: true, addCheckInfo: true};
        $scope.showShi = UserServer.showShi;
        $scope.showQuxian = UserServer.showQuxian;
        $scope.submit = self.submit;
        $scope.console = console;
        $scope.detailIntegratedAudit = self.detailIntegratedAudit;
        $scope.hideCheckDepartment = self.hideCheckDepartment;
        $scope.exportExcel = self.exportExcel;
    }]);
