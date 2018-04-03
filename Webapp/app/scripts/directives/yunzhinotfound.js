'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiNotFound
 * @description
 * # yunzhiNotFound
 */
angular.module('webappApp')
    .directive('yunzhiNotFound', ['$uibModal', 'ToMessageService', 'CommonService',
        function ($uibModal, ToMessageService, CommonService) {
            return {
                scope: {
                    ngModel: '=',
                    instrumentType: '=',            // 二级器具类别
                    instrumentFirstLevelType: '='  // 一级器具类别
                },
                templateUrl: 'views/directive/yunzhiNotFound.html',
                restrict: 'EA',
                link: function postLink(scope) {
                    // 用户点击没找到按钮
                    scope.notFound = function () {
                        console.log('hello');
                        // 显示弹出框
                        $uibModal.open({
                            templateUrl: 'views/directive/yunzhiNotFoundModal.html',
                            controller: ['$scope', '$uibModalInstance', function ($scope, $uibModalInstance) {
                                // 设置标题
                                $scope.title = '请输入未找到的' + scope.ngModel + '名称';
                                // 用户点击确认按钮
                                $scope.ok = function () {
                                    // 关闭弹窗
                                    $uibModalInstance.close();
                                    // 消息内容
                                    var messageContent = scope.instrumentFirstLevelType.name + '->' + scope.instrumentType.name + ' ' + scope.ngModel + '未找到，用户想添加的精确度的值为：' + scope.name;

                                    // 消息实体
                                    var data = {
                                        title: scope.ngModel + '未找到',
                                        content: messageContent
                                    };
                                    // 向当前用户所属市级管理部门发送消息
                                    ToMessageService.sendMessageToManagementDepartmentOfCurrentUser(data, function () {
                                        // 提示用户发送消息的作用
                                        CommonService.success('发送成功', '已向市级管理部门发出消息提醒，请耐心等待市级管理部门处理，并留意收件箱', function () {
                                        });
                                    });

                                };

                                // 用户点击取消按钮
                                $scope.cancel = function () {
                                    $uibModalInstance.close();
                                };
                            }]
                        });
                    };


                }
            };
        }]);
