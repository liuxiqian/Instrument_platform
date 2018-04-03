'use strict';

/**
 * @ngdoc service
 * @name webappApp.StandardDeviceService
 * @description
 * # StandardDeviceService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('StandardDeviceService', ['$http', 'CommonService', function ($http, CommonService) {
        var self = this;
        var deleteUrl = '/StandardDevice/delete/';
        var updateUrl = '/StandardDevice/update/';
        self.baseUrl = self.saveUrl = '/StandardDevice';

        //处理数据
        self.dealData = function (data, value) {
            data.deviceSet = {id: value.deviceSet.id};
            data.standardDeviceInstrumentType = {id: value.standardDeviceInstrumentType.id};
            data.accuracy = {id: value.accuracy.id};
            data.measureScale = {id: value.measureScale.id};
            data.specification = value.specification.id ? {id: value.specification.id} : undefined;
            return data;
        };

        //保存数据
        self.save = function (object, callback) {

            //发送请求
            $http.post(self.saveUrl, object)
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        //更新数据
        self.update = function (id, data, callback) {
            //发送请求
            $http.put(updateUrl + id, data)
                .then(function success(response) {
                    if(callback) {callback(response.data);}
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        //删除数据
        self.delete = function (standardDeviceId, callback) {
            //发送请求
            $http.delete(deleteUrl + standardDeviceId)
                .then(function success(response) {
                    if (callback) {
                        callback(response.status);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        self.findById = function(id) {
            return $http.get(self.baseUrl + '/' + id);
        };

        return {
            findById: self.findById,
            save: self.save,
            update: self.update,
            delete: self.delete
        };
    }
    ]);
