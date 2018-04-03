'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:SystemApplyFieldIndexCtrl
 * @description
 * # SystemApplyFieldIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('SystemApplyFieldIndexCtrl', ['$stateParams', '$http', 'ApplyFieldService', 'CommonService', '$scope', '$state', 'ApplyTypeService', function ($stateParams, $http, ApplyFieldService, CommonService, $scope, $state, ApplyTypeService) {
        var self = this;

        // 初始化
        self.init = function () {
            showAllApplyType();
            $scope.applyType = {id: parseInt($stateParams.applyTypeId)};
            ApplyFieldService.getAllByApplyTypeId($scope.applyType.id, function (response) {
                $scope.datas = response;
            });
        };

        // 获取全部的申请类型
        var showAllApplyType = function () {
            ApplyTypeService.all(function(response) {
                $scope.applyTypes = response;
            });
        };
        

        //删除方法实现
        self.delete = function (index, id) {
            //提示用户
            CommonService.warning(function (success, error) {
                ApplyFieldService.delete(id, function (response) {
                    if (204 === response.status) {
                        // 删除此条数据，更新视图
                        $scope.datas.splice(index, 1);
                        success();
                    } else {
                        // 未删除关联实体
                        error('error', '请先删除与其相关联的其它记录', '');
                    }
                });
            });
        };

        // 监视ApplyTypeID
        self.watchApplyTypeId = function(newValue, oldValue) {
            // 如果新旧值不相同, 则重新加载
            if (newValue !== oldValue) {
                $stateParams.applyTypeId = $scope.applyType.id; 
                // 使用{reload: true}来保证重新加载数据
                $state.transitionTo($state.current, $stateParams, {reload: true});
            }
        };
        

        // 执行获取数据
        self.init();
        $scope.delete = self.delete;
        // 监视ID值的效率要高于监视整个对象。
        $scope.$watch('applyType.id', self.watchApplyTypeId);

    }]);
