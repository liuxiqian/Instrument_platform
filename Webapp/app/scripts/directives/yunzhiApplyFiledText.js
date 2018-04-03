'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiApplyFiledText
 * @description
 * # yunzhiApplyFiledText
 */
angular.module('webappApp')
    .directive('yunzhiApplyFiledText', function(WorkflowNodeAndApplyFieldAccessService) {
        return {
            template: '<div ng-include="getContentUrl()" ngModel="ngModel"></div>',
            scope: {
                ngModel: '=',
                work: '='
            },
            restrict: 'EA',
            link: function postLink(scope, element) {
                var self = this;
                scope.access = 'Read';

                WorkflowNodeAndApplyFieldAccessService.getByWorkflowNodeIdAndApplyFieldId(
                    scope.work.workflowNode.id, scope.ngModel.id, function(workflowNodeAndApplyFieldAccess){
                        if (workflowNodeAndApplyFieldAccess === null || workflowNodeAndApplyFieldAccess.read === false) {
                            element.text('');
                        } else if (scope.work.done === true && workflowNodeAndApplyFieldAccess.read === true) {
                            console.log('工作变结，且有读的权限，则调用读指令');
                        } else if (workflowNodeAndApplyFieldAccess.write === true) {
                            console.log('工作未办结，有写的权限，则调用编辑指令');
                            scope.access = 'Write';
                        } else {
                            console.log('工作未办结，但有读的权限，则调用读指令');
                        }
                    });

                self.getContnetUrlByAccess = function(access) {
                    return 'views/directive/yunzhiApplyFieldText' + access + '.html';
                };

                // console.log(scope.work.apply);

            	// 要把申请（带着申请字段记录）传过来，因为要根据申请去取数据表中的记录。
            	// 在更新申请时，一并更新申请字段记录
            	
                scope.getContentUrl = function() {
                	return self.getContnetUrlByAccess(self.access);
                };
            }	
        };
    });
