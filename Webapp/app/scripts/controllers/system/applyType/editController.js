'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:systemApplyTypeEditCtrl
 * @description     系统设置-申请类别管理-编辑
 * # systemApplyTypeEditCtrl
 * @author zhangjiahao
 */
angular.module('webappApp')
    .controller('systemApplyTypeEditCtrl', ['$scope', 'ApplyTypeService', 'CommonService', '$stateParams', function($scope, ApplyTypeService, CommonService, $stateParams) {
        // 数据初始化
        var self = this;
        self.applyTypeId = parseInt($stateParams.applyTypeId ? $stateParams.applyTypeId : 0); //获取申请类型id

        self.getById = function() {
            ApplyTypeService.getById(self.applyTypeId,  function (data){
                $scope.data = data;
            });
        };
        self.getById();

        self.update = function() {
            ApplyTypeService.update($scope.data.id, $scope.data, function () {
                CommonService.success();
            });
        };

        $scope.update = self.update;
    }]);
