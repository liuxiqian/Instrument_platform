'use strict';

/**
 * @ngdoc service
 * @name webappApp.StandardAuthorizationService
 * @description
 * # StandardAuthorizationService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('StandardAuthorizationService', ['$http', 'CommonService', function($http, CommonService) {
        var self = this;
        var getAllByDeviceSetIdUrl = '/DeviceSet/';
        var saveUrl = '/DeviceInstrument/';
        var pageAllByCurrentUserOfDeviceInstrumentUrl = '/DeviceInstrument/pageAllByCurrentUserOfDeviceInstrument';
        var updateDeviceInstrumentsByIdUrl = '/DeviceSet/updateDeviceInstrumentsById/';

        //处理save的数据
        self.dealSaveData = function(value) {
            var deviceSet = value.deviceSet;
            var deviceInstruments = [];
            //先处理数据原来的数据
            angular.forEach(deviceSet.deviceInstruments, function(data) {
                var forEachDeviceInstrument = {
                    id: {
                        'accuracyId': data.accuracy.id,
                        'measureScaleId': data.measureScale.id
                    }
                };
                deviceInstruments.push(forEachDeviceInstrument);
            });

            //增加新添加的数据
            var deviceInstrument = {
                id: {
                    'accuracyId': value.accuracy.id,
                    'measureScaleId': value.measureScale.id
                }
            };
            deviceInstruments.push(deviceInstrument);

            deviceSet.deviceInstruments = deviceInstruments;
            //处理部门的数据
            // deviceSet.department = {
            //     id: deviceSet.department.id
            // };
            //返回数据
            return deviceSet;
        };

        self.getAllByDeviceSetId = function(deviceSetId, callback) {
            $http.get(getAllByDeviceSetIdUrl + deviceSetId)
                .then(function success(response) {
                    callback(response.data);
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        self.save = function(object, callback) {
            $http.post(saveUrl, object)
                .then(function success() {
                    if (callback) {
                        callback();
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        self.pageAllByCurrentUserOfDeviceInstrument = function(callback) {
            $http.get(pageAllByCurrentUserOfDeviceInstrumentUrl)
                .then(function success(response) {
                    if (callback) {
                        //处理返回的数据然后传给C层,主要是处理content
                        var newContent = [];
                        angular.forEach(response.data.content, function(data) {
                            //data是个数组,我的目的是处理成对象
                            var object = {};
                            //将授权鉴定项目对象给赋给object
                            object = data[0];
                            //将授权鉴定项目对应的计量标准装置实体赋值
                            object.deviceSet = data[1];
                            //将相应的器具类别关联
                            object.instrumentType = data[2];
                            //然后赋值newContent
                            newContent.push(object);
                        });
                        //将放回数据的content二维数据转化成一维数组
                        response.data.content = newContent;
                        //将处理好的数据返回给V层
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        self.updateDeviceInstrumentsById = function(deviceInstrument, objectId, callback) {
            //遍历出devoceSet数组中的要删除的授权鉴定项目
            angular.forEach(deviceInstrument.deviceSet.deviceInstruments, function(forEachDeviceInstrument, index) {
                //如果判断两个授权检定项目相等就删除
                if (angular.equals(forEachDeviceInstrument.id, objectId)) {
                    deviceInstrument.deviceSet.deviceInstruments.splice(index, 1);
                }
            });

            //处理发送的数据
            var resultData = self.dealSaveData(deviceInstrument);

            //向后台发出更新请求
            $http.put(updateDeviceInstrumentsByIdUrl + resultData.id, resultData)
                .then(function success() {
                    callback();
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        self.pageAllOfCurrentUserBySpecification = function(params, callback) {
            var url = '/DeviceInstrument/pageAllOfCurrentUserBySpecification';
            $http.get(url, { params: params })
                .then(function success(response) {
                    callback(response.data);
                }, function(response) {
                    CommonService.httpError(response);
                });
        };

        // 新增数据界面的初始化
        self.setDeviceSet = function(deviceSet) {
            self.deviceSet = deviceSet;
        };

        return {
            getAllByDeviceSetId: self.getAllByDeviceSetId,
            save: self.save,
            pageAllByCurrentUserOfDeviceInstrument: self.pageAllByCurrentUserOfDeviceInstrument,
            delete: self.delete,
            updateDeviceInstrumentsById: self.updateDeviceInstrumentsById,
            dealDeviceSetSaveData: self.dealSaveData,
            pageAllOfCurrentUserBySpecification: self.pageAllOfCurrentUserBySpecification,
            setDeviceSet: self.setDeviceSet
        };
    }]);
