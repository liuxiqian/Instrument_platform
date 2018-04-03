'use strict';

/**
 * @ngdoc service
 * @name webappApp.WorkflowNodeAndApplyFieldAccessService
 * @description
 * # WorkflowNodeAndApplyFieldAccessService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('WorkflowNodeAndApplyFieldAccessService', function(CommonService, $http) {
        var self = this;
        self.getByWorkflowNodeIdAndApplyFieldId = function(workflowNodeId, applyFieldId, callback) {
            var url = '/WorkflowNodeAndApplyFieldAccess/getByWorkflowNodeIdAndApplyFieldId/workflowNodeId/' + workflowNodeId + '/applyFieldId/' + applyFieldId;
            $http.get(url)
                .then(function success(response){
                	if (callback) {callback(response.data);}
                }, function error(response){
                    CommonService.httpError(response);
                });
        };

        return {
            getByWorkflowNodeIdAndApplyFieldId: self.getByWorkflowNodeIdAndApplyFieldId
        };

        // AngularJS will instantiate a singleton by calling "new" on this function
    });
