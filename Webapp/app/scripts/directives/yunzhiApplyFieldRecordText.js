'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiApplyFiledText
 * @description 申请字段类型  文本
 * # yunzhiApplyFiledText
 */
angular.module('webappApp')
    .directive('yunzhiApplyFieldRecordText', function() {
            return {
                template: '<div ng-include="getContentUrl()"></div>',
                scope: {
                    ngModel: '=',
                    work: '='
                },
                restrict: 'EA',
                link: function postLink(scope) {
                    var self = this;

                    // 根据工作流结点及申请字段获取权限
                    self.getAccessByWorkflowNodeAndApplyField = function(workflowNode, applyField) {
                        var found = false;
                        var access;
                        angular.forEach(applyField.workflowNodeAndApplyFieldAccesses, function(value) {
                            if (!found && value.workflowNode.id === workflowNode.id) {
                                found = true;
                                access = value;
                            }
                        });
                        return access;
                    };

                    self.getContnetUrlByAccess = function(access) {
                        return 'views/directive/yunzhiApplyFieldText' + access + '.html';
                    };

                    // 根据当前权限及工作状态，决定是不返回任何信息，还是返回写信息，读信息
                    scope.getContentUrl = function() {
                        var accessObject = self.getAccessByWorkflowNodeAndApplyField(scope.work.workflowNode, scope.ngModel.applyField);
                        self.access = 'Read';
                        scope.showHtml = true;
                        if (typeof(accessObject) === 'undefined' || accessObject.read === false) {
                            scope.showHtml = false;
                        } else if (scope.work.done === true && accessObject.read === true) {
                            self.access = 'Read';
                        } else if (accessObject.write === true) {
                            self.access = 'Write';
                        } else {
                            self.access = 'Read';
                        }

                        return self.getContnetUrlByAccess(self.access);
                    };
                }
            };
    });
