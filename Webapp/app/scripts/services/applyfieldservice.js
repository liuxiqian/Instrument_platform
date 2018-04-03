'use strict';

/**
 * @ngdoc service
 * @name webappApp.ApplyFieldService
 * @description
 * # 申请字段service层
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('ApplyFieldService', function($http, CommonService) {
        var self = this;
        self.getAllByApplyTypeId = function(applyTypeId, callback) {
            var url = '/ApplyField/getAllByApplyTypeId/' + applyTypeId;
            $http.get(url)
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        self.save = function(data, callback) {
            var url = '/ApplyField/';
            $http.post(url, data)
                .then(function success(response) {
                    if (callback) { callback(response.data); }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        self.update = function(id, data, callback) {
            $http.put('/ApplyField/' + id, data)
                .then(function success(response) {
                    if (callback) {
                        callback(response.status);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        self.delete = function(id, callback) {
            $http.delete('/ApplyField/' + id)
                .then(function success(response) {
                    if (callback) {
                        callback(response);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        self.getOneById = function(id, callback) {
            var url = '/ApplyField/' + id;
            $http.get(url)
                .then(function success(response) {
                    if (callback) { callback(response.data); }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        return {
            getAllByApplyTypeId: self.getAllByApplyTypeId,
            save: self.save,
            update: self.update,
            delete: self.delete,
            getOneById: self.getOneById
        };
    });
