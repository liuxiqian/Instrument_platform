'use strict';

/**
 * @ngdoc service
 * @name webappApp.MeasuringScaleService
 * @description
 * # MeasuringScaleService
 * 测量范围Service层
 */
angular.module('webappApp')
    .service('MeasuringScaleService', ['$http', 'CommonService', function ($http, CommonService) {
        var self = this;
        /**
         * 获取某个器具类别的所有实体信息
         * @param instrumentTypeId
         * @param callback
         * panjie
         */
        self.getAllByInstrumentTypeId = function (instrumentTypeId, callback) {
            var url = '/MeasureScale/getAllByInstrumentTypeId/' + instrumentTypeId;
            $http.get(url)
                .then(function success(response) {
                    callback(response.data);
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };
        return {
            getAllByInstrumentTypeId: self.getAllByInstrumentTypeId
        };
    }]);
