'use strict';

/**
 * @ngdoc service
 * @name webappApp.AccuracyService
 * @description 准确度等级Service层
 * # AccuracyService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('AccuracyService', ['$http', 'CommonService', function ($http, CommonService) {
        var self = this;

        /**
         * 获取某个器具类别下的所有实体
         * @param instrumentTypeId 器具类别id
         * @param callback
         * panjie
         */
        self.getAllByInstrumentTypeId = function(instrumentTypeId, callback) {
            var url = '/Accuracy/getAllByInstrumentTypeId/' + instrumentTypeId;
            $http.get(url)
                .then(function success(response){
                    if (callback) {callback(response.data);}
                }, function error(response){
                    CommonService.httpError(response);
                });
        };

        // 添加
        self.save = function (data, callback) {
            $http.post('/Accuracy/', data)
                .then(function success(response) {
                    if (response.status === 201) {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        // 删除
        self.deleteById = function (id, callback) {
            var url = '/Accuracy/' + id;
            $http.delete(url)
                .then(function success(response) {
                    if (callback) {
                        callback(response);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        return {
            getAllByInstrumentTypeId: self.getAllByInstrumentTypeId,
            save: self.save,
            deleteById: self.deleteById
        };
    }]);
