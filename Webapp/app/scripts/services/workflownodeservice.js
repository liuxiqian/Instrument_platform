'use strict';

/**
 * @ngdoc service
 * @name webappApp.WorkflowNodeService
 * @description
 * # WorkflowNodeService
 * Service in the webappApp.
 * 审核流程结点表,记录审核流一步步的信息
 */
angular.module('webappApp')
.service('WorkflowNodeService', function (WebAppMenuService,  $http, CommonService) {
	var self = this;
	/**
	 * 获取当前菜单，当前登录用户适用的审核结点信息
	 */
	self.getAllOfCurrentWebAppMenuAndCurrentLoginUser = function(callback) {
		WebAppMenuService.getCurrentMenu(function(menu) {
			console.log(menu);
			var url;
			url = '/WorkflowNode/getAllByWebAppMenuIdOfCurrentUser/' + menu.id;
			$http.get(url)
			.then(function success(response) {
				if (callback) {callback(response.data);}
			}, function error(response){
                CommonService.httpError(response);
			});
		});
	};
	
	/**
	 * 获取传入结点的 下一结点数组信息 每个结点，可能对应多个下级结点
	 * @param workflowNodeId
	 * @param callback
	 * @author panjie
	 */
	self.getAllByPreWorkflowNodeId = function(workflowNodeId, callback) {
		var url;
		url = '/WorkflowNode/getAllByPreWorkflowNodeId/' + workflowNodeId;
		$http.get(url)
		.then(function success(response) {
			if (callback) {callback(response.data);}
		}, function error(response){
            CommonService.httpError(response);
		});
	};
	
	/**
	 * 获取当前用户， 在某个申请类型下的首个工作流结点
	 * @param callback
	 */
	self.getStartOneOfCurrentUserByApplyType  = function(applyType, callback) {
		var url = '/WorkflowNode/getTopOneByApplyTypeIdOfCurrentUser/' + applyType.id;
		$http.get(url)
		.then(function success(response){
			if (callback) {callback(response.data);}
		}, function error(response){
            CommonService.httpError(response);
		});
	};
	
	return {
		getAllOfCurrentWebAppMenuAndCurrentLoginUser: self.getAllOfCurrentWebAppMenuAndCurrentLoginUser,
		getAllByPreWorkflowNodeId: self.getAllByPreWorkflowNodeId,
		getNextListsByWorkflowNodeId: self.getAllByPreWorkflowNodeId,
		getStartOneOfCurrentUserByApplyType: self.getStartOneOfCurrentUserByApplyType,
		getStartOneByWebAppMenuIdOfCurrentUser: self.getStartOneByWebAppMenuIdOfCurrentUser
	};
});
