'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:DepartmentIndexCtrl
 * @description 部门(对应前台四种用户管理)管理
 * # DepartmentIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('DepartmentIndexCtrl', ['$scope',
        'departmentService',
        '$location',
        'CommonService',
        '$stateParams',
        'config',
        '$state',
        'UserServer',
        function($scope, departmentService, $location, CommonService, $stateParams, config, $state, UserServer) {
            var self = this;

            self.init = function() {
                CommonService.initControllerPage(self, $scope);
                self.type = $stateParams.type;
                $scope.params = self.initScopeParams();
                self.load();
            };

            self.initScopeParams = function() {
                return {
                    page: $stateParams.page ? parseInt($stateParams.page) : 0,
                    size: $stateParams.size ? $stateParams.size : config.size
                };
            };

            // 定义获取数据方法
            self.load = function() {
                // 获取后台数据
                departmentService.pageByDepartmentTypePinyinOfCurrentUserManageDistricts(self.type, $scope.params, function(data) {
                    $scope.data = data;
                });
            };

            // 初始化
            self.init();

            // 删除功能
            self.delete = function(index, id) {
                //提示用户
                CommonService.warning(function(success, error) {
                    departmentService.delete(id, function(response) {
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

            //判断编辑功能的去向
            switch (self.type) {
                case 'jishujigou':
                    self.ctrlName = 'technicalInstitution';
                    break;
                case 'shengchanqiye':
                    self.ctrlName = 'enterprise';
                    break;
                case 'guanlibumen':
                    self.ctrlName = 'management';
                    break;
                case 'qijuyonghu':
                    self.ctrlName = 'instrumentUser';
                    break;
                default:
                    break;
            }

            /**
             * 注册新用户
             * panjie
             */
            self.add = function() {
                $state.go('department.' + self.ctrlName + 'Add', {});
            };

            /**
             * 编辑用户
             * @param  {部门} department 部门
             * @return {[type]}            [description]
             * panjie
             */
            self.edit = function(department) {
                var param = {id: department.id};
                $state.go('department.' + self.ctrlName + 'Edit', param);
            };

            /**
             * 是否显示区县
             * @return {[type]} [description]
             */
            self.showQuxian = function() {
                return UserServer.showQuxian();
            };

            /**
             * 是否显示市
             * @return {[type]} [description]
             */
            self.showShi = function() {
                return UserServer.showShi();
            };

            $scope.add = self.add;
            $scope.edit = self.edit;
            $scope.showQuxian = self.showQuxian;
            $scope.showShi = self.showShi;
            $scope.delete = self.delete;
        }
    ]);
