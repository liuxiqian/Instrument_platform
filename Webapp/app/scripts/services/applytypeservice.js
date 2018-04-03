'use strict';

/**
 * @ngdoc service
 * @name webappApp.ApplyTypeService
 * @description
 * # ApplyTypeService
 * 申请类型
 */
angular.module('webappApp')
    .service('ApplyTypeService', function($http, WorkflowNodeService, CommonService) {
        var self = this;
        self.data = {};
        self.data.all = [];
        self.getOneByName = function(name, callback) {
            var url = '/ApplyType/getOneByName/' + name;
            $http.get(url)
                .then(function success(response) {
                    if (callback) { callback(response.data); }
                }, function error(response) {
                    CommonService.httpError(response);
                });

        };

        self.getById = function(id, callback) {
            var url = '/ApplyType/getById/' + id;
            $http.get(url)
                .then(function success(response) {
                    if (callback) { callback(response.data); }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        /**
         * 获取 强检备案申请 的申请类型以及工作流结点首结点
         * @param callback
         */
        self.getApplyTypeAndStartWorkflowNodeByApplyTypeId = function(applyTypeId, callback) {
            // 获取申请类型
            self.getById(applyTypeId, function(applyType) {
                // 获取工作流结点
                WorkflowNodeService.getStartOneOfCurrentUserByApplyType(applyType, function(workflowNode) {
                    if (callback) {
                        callback(applyType, workflowNode);
                    }
                });
            });
        };

        self.all = function (callback) {
            if (self.data.all.length === 0) {
                $http.get('/ApplyType/getAll').then(function (response) {
                    self.data.all = response.data;
                    callback(self.data.all);
                });
            } else {
                callback(self.data.all);
            }
        };

        self.delete = function(id, callback) {
            $http.delete('/ApplyType/' + id)
                .then(function success(response) {
                    if (callback) {
                        callback(response);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        self.save = function (data, callback) {
            var url = '/ApplyType/';
            $http.post(url, data)
                .then(function success(response){
                    if (callback) {callback(response.data);}
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        self.update = function (id, data, callback) {
            $http.put('/ApplyType/' + id, data)
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                        console.log(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        return {
            getById: self.getById,
            getOneByName: self.getOneByName,
            getApplyTypeAndStartWorkflowNodeByApplyTypeId: self.getApplyTypeAndStartWorkflowNodeByApplyTypeId,
            all: self.all,
            delete: self.delete,
            save: self.save,
            update: self.update

        };
        // AngularJS will instantiate a singleton by calling "new" on this function
    });
