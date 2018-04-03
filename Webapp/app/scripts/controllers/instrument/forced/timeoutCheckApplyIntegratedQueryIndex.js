'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:InstrumentForcedIntegratedquerytimeoutcheckapplyindexCtrl
 * @description 强检器具超期检定备案管理 - 综合查询(器具用户)
 * # InstrumentForcedIntegratedquerytimeoutcheckapplyindexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('InstrumentForcedTimeoutCheckApplyIntegratedQueryIndexCtrl',
        function ($scope, mandatoryInstrumentService) {
            var self = this;
            mandatoryInstrumentService.initController(self, $scope);
            self.init = function () {
                $scope.params = self.initScopeParams();
                self.load();
            };

            // 获取查询参数
            self.generateQueryParams = function () {
                // 整理传入的参数信息。我们之所以在params的属性中只使用了基本类型，是为了以后将查询条件放到url中做准备
                var params = {
                    page: $scope.params.page,
                    size: $scope.params.size,
                    id: $scope.params.id,
                    disciplineId: $scope.params.discipline.id,
                    instrumentTypeFirstLevelId: $scope.params.instrumentTypeFirstLevel.id,
                    instrumentTypeId: $scope.params.instrumentType.id,
                    audit: $scope.params.audit,
                    name: $scope.params.name,
                    overdue: $scope.params.overdue,   // 是否超期 本项由路由指定
                    status: $scope.params.status     // 器具状态 本项由路由指定
                };
                return params;
            };

            self.init();
        });
