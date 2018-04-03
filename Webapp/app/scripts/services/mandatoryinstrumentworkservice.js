'use strict';

/**
 * @ngdoc service
 * @name webappApp.MandatoryInstrumentWorkService
 * @description
 * # MandatoryInstrumentWorkService
 * 强检器具申请
 */
angular.module('webappApp')
    .service('MandatoryInstrumentWorkService', function ($http, CommonService) {
        var self = this;

        /**
         * 审核
         * @param    {object}                 $http
         * @return   {callback}
         * @author 梦云智 http://www.mengyunzhi.com
         * @DateTime 2017-07-19T10:20:51+0800
         */
        self.auditByIdOfCurrentUser = function (workId, data, callback) {
            var url = "/MandatoryInstrumentWork/auditByIdOfCurrentUser/" + workId;
            $http.put(url, data)
                .then(function success(response) {
                    if (callback) {
                        callback(response.stauts);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        /**
         * 审核某个工作
         * @param workId 工作ID
         * @param opinion 审核意见
         * @param department 审核部门
         * @param auditType 审核类型（办结，送下一部门...）
         * @param workflowNode 工作流结点
         * @param callback
         */
        self.auditByWorkIdAndOpinionAndDepartmentAndAuditTypeAndWorkflowNode = function (workId, opinion, department, auditType, workflowNode, callback) {
            var data = {};
            data.opinion = opinion;
            data.department = department;
            data.type = auditType;
            data.nextWorkflowNode = workflowNode;
            var url = '/MandatoryInstrumentWork/auditById/' + workId;
            $http.patch(url, data)
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        /**
         * 获取分页数据
         * @param params 综合查询参数
         * @param callback
         * panjie
         */
        self.pageOfCurrentUser = function(params, callback) {
            var url = '/MandatoryInstrumentWork/pageAllOfCurrentLoginUserBySpecification';
            $http.get(url, {params: params})
                .then(function success(response){
                    if (callback) {callback(response.data);}
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        self.save = function(data, callback) {
            var url = '/MandatoryInstrumentWork';
            $http.post(url, data)
                .then(function success(response){
                    if (callback) {callback(response.data);}
                }, function error(response){
                    CommonService.httpError(response);
                });
        };

        return {
            pageOfCurrentUser: self.pageOfCurrentUser,
            save: self.save,
            auditByIdOfCurrentUser: self.auditByIdOfCurrentUser,
            auditByWorkIdAndOpinionAndDepartmentAndAuditTypeAndWorkflowNode: self.auditByWorkIdAndOpinionAndDepartmentAndAuditTypeAndWorkflowNode
        };
    });
