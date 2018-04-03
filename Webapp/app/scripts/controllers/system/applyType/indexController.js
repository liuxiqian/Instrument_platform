'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:SystemApplyTypeIndexCtrl
 * @description
 * # SystemApplyTypeIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('systemApplyTypeIndexCtrl', ['$stateParams',
        '$http',
        'ApplyTypeService',
        'CommonService',
        '$scope',
        function($stateParams,
            $http,
            ApplyTypeService,
            CommonService,
            $scope) {

            var self = this;
            // 定义获取数据的方法
            var showData = function() {
                //获取后台数据
                ApplyTypeService.all(function(response) {
                    $scope.datas = response;
                    console.log(response);
                });
            };

            // 执行获取数据
            showData();

            //删除方法实现
            self.delete = function(index, data) {
                //提示用户
                console.log($scope.data);
                CommonService.warning(function(success, error) {
                    ApplyTypeService.delete(data.id, function(response) {
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

            $scope.delete = self.delete;
        }
    ]);
