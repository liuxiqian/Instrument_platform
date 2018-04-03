'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:InstrumenttypeCtrl
 * @description
 * 器具类别管理  器具列表
 * @author panjie
 */
angular.module('webappApp')
    .controller('InstrumentTypeCtrl', function ($scope, CommonService, InstrumentTypeService, config, $stateParams) {
        var self = this;
        CommonService.initControllerPage(self, $scope);

        self.init = function () {
            $scope.params = self.initScopeParams();
            $scope.config = self.config = $stateParams.config;
            $scope.type = self.type = $scope.config.type;
            self.load();

            // 方法暴露
            $scope.delete = self.delete;
            // 当学科类别发生变化时，重新加载
            $scope.$watch('params.discipline', function(newValue, oldValue) {
                // 使用以下判断，防止在初始化时，造成重载页面的死循环。
                if (newValue && oldValue && (newValue.id !== oldValue.id)) {
                    self.reload();
                }
            });
        };

        self.initScopeParams = function () {
            return {
                discipline: $stateParams.discipline,
                page: $stateParams.page,
                size: $stateParams.size
            };
        };

        // 获取数据
        self.load = function () {
            if ($scope.params.discipline.id) {
                InstrumentTypeService.pageByDisciplineId($scope.params.discipline.id, self.generateQueryParams(), self.type, function (data) {
                    $scope.data = data;
                });
            }
        };

        //删除方法实现
        self.delete = function (index, id) {
            //提示用户
            CommonService.warning(function (success, error) {
                InstrumentTypeService.delete(id, function (response) {
                    if (204 === response.status) {
                        // 删除此条数据，更新视图
                        $scope.data.content.splice(index, 1);
                        success();
                    } else {
                        // 未删除关联实体
                        error('error', '请先删除与其相关联的其它记录', '');
                    }
                });
            });

        };

        self.init();
    });
