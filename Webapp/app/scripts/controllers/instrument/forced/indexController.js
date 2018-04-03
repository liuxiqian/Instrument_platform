'use strict';

/**
 * @ngdoc function
 * @名称 webappApp.controller:MandatoryIntegratedIndexCtrl
 * @描述：控制V层数据的显示样式，并且调用M层的方法获取后台数据。
 * #MandatoryIntegratedIndexCtrl
 * 强检器具备案 综合查询 （适用于器具用户）
 * Controller of the webappApp（控制层）
 */
angular.module('webappApp')
    .controller('InstrumentForcedIndexCtrl', ['$scope',
        '$location',
        'mandatoryInstrumentService',
        'sweetAlert',
        'config',
        '$state',
        'CommonService',
        function ($scope,
                  $location,
                  mandatoryInstrumentService,
                  sweetAlert,
                  config,
                  $state,
                  CommonService) {
            var self = this;
            mandatoryInstrumentService.initController(self, $scope);

            // 分页数据初始化
            self.init = function () {
                $scope.params = self.initScopeParams();
                self.load();
            };

            // 跳转至检定档案管理界面
            self.detailIntegratedAudit = function (mandatoryInstrument) {
                var stateParams = {};
                stateParams.disciplineId = self.params.discipline.id;
                stateParams.instrumentTypeFirstLevelId = self.params.instrumentTypeFirstLevel.id;
                stateParams.instrumentTypeId = self.params.instrumentType.id;
                stateParams.name = self.params.name;
                stateParams.mandatoryInstrumentId = mandatoryInstrument.id;
                $state.go('mandatory.integratedAudit', stateParams, {reload: true});
            };

            // 删除
            self.delete = function (object) {
                CommonService.warning(function (success, error) {
                    mandatoryInstrumentService.delete(object.id, function (response) {
                        if (response.status === 204) {
                            // 隐藏该实体
                            object.hide = true;
                            success();
                        } else {
                            error(response);
                        }
                    });
                });
            };

            // 当状态为退回时，显示删除按钮
            self.showDelete = function (object) {
                return self.canBeDelete(object);
            };

            // 当前器具是否可以被删除
            self.canBeDelete = function () {
                return false;
                // if (object.status === -2) {
                //     // 被退回的可删除
                //     return true;
                // } else {
                //     return false;
                // }
            };

            /**
             * 为当前的器具用户导出excel
             * @author chuhang
             */
            self.exportExcel = function () {
                var params = self.generateQueryParams();
                mandatoryInstrumentService.exportExcelOfCurrentUser(params, function () {
                });
            };


            self.init();
            // 方法统一暴露
            $scope.isShow = {};
            $scope.isShow.operation = true;
            $scope.showCheckAll = true;
            $scope.reload = self.reload;
            $scope.delete = self.delete;
            $scope.showAudit = self.showAudit;
            $scope.console = console;
            $scope.detailIntegratedAudit = self.detailIntegratedAudit;
            $scope.showDelete = self.showDelete;
            $scope.delete = self.delete;
            $scope.exportExcel = self.exportExcel;
        }
    ]);
