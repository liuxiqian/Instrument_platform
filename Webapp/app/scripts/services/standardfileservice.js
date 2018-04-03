'use strict';

/**
 * @ngdoc service
 * @名称 webappApp.standardFileService
 * @描述：save和all方法的具体实现。
 * # standardFileService——“标准装置-档案查询”的M层
 * Service in the webappApp.（模型层）
 */
angular.module('webappApp')
    .service('standardFileService', ['$http', 'CommonService', function($http, CommonService) {
        var self = this;

        self.save = function(data, callback) {
            $http.post('/DeviceSet/', data)
                .then(function success(response) {
                if (callback) {
                    callback(response.data);
                }
            }, function error(response) {
                CommonService.httpError(response);
            });
        };

        self.all = function(callback) {
            $http.get('/DeviceSet/getAll').then(function success(response) {
                    var data = response.data;
                    callback(data);
                },
                function error(response) {
                    CommonService.httpError(response);
                });
        };

        /**
         * 获取带有多条件查询的分页信息
         * params: {
         *  districtId
         *  standardDepartmentId
         *  name
         *  code
         * }
         */
        self.pageAllOfCurrentUserBySpecification = function (params, callback) {
            var url = '/DeviceSet/pageAllOfCurrentUserBySpecification';
            $http.get(url, {params: params})
                .then(function success(response) {
                    callback(response.data);
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        //获取当前登录用户所有标准装置
        self.pageAllOfCurrentUser = function (params, callback) {
            var url = '/DeviceSet/pageAllOfCurrentUser';
            var queryParams = CommonService.querySerialize(params);
            $http.get(url + '?' + queryParams)
                .then(function success (response) {
                    callback(response.data);
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        self.getAllByDeviceSetId = function (deviceSetId, callback) {
            $http.get('/StandardDevice/getAllByDeviceSetId/' + deviceSetId)
                .then(function success(response) {
                    callback(response.data);
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        self.update = function (deviceSetId, deviceSet, callback) {
            $http.put('/DeviceSet/' + deviceSetId, deviceSet)
                .then(function success(response) {
                    if (callback) {callback(response.data);}
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        self.delete = function (deviceSet, callback) {
            //向后台发出请求
            $http.delete('/DeviceSet/' + deviceSet.id)
                .then(function success(response) {
					if (callback) {callback(response.status);}
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        return {
            save: self.save,
            all: self.all,
            pageAllOfCurrentUserBySpecification: self.pageAllOfCurrentUserBySpecification,
            pageAllOfCurrentUser: self.pageAllOfCurrentUser,
            getAllByDeviceSetId: self.getAllByDeviceSetId,
            update: self.update,
            delete: self.delete
        };
    }]);
