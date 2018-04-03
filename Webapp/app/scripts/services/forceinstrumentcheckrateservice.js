'use strict';

/**
 * @ngdoc service
 * @name webappApp.forceInstrumentCheckRateService
 * @description
 * # forceInstrumentCheckRateService
 * zhangxishuo
 */
angular.module('webappApp')
    .service('forceInstrumentCheckRateService', function ($http, CommonService) {
        var self = this;

        /**
         * 根据多年度查询数据
         * zhangxishuo
         */
        self.queryByYears = function(years, district, instrumentUserDepartment, purpose, instrumentType, callback) {

            // 如果未选中区域，终止运行，避免提示框报错
            if (typeof(district.id) === 'undefined') {
                return;
            }
            
            // 拼接url
            var url = '/InstrumentCheckedRate/getAllCheckedRate/years/';
            // forEach将数组组装到url中
            angular.forEach(years, function (value, key) {
                url = url + value;
                // 如果不是最后一个年度，则添加一个逗号，用于分隔数组
                if (key !== years.length - 1) {
                    url += ',';
                }
            });
            // 在url中添加区域信息
            url = url + '/districtId/' + district.id;

            // 设置请求参数
            var params = {
                instrumentUserDepartmentId: instrumentUserDepartment.id,   // 器具用户
                purposeId: purpose.id,                                     // 用途
                instrumentTypeId: instrumentType.id                        // 器具类别
            };

            // 发起请求
            $http.get(url, {params: params})
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);                           // 成功，回调
                    }
                }, function error(response) {
                    CommonService.httpError(response);                     // 失败，统一处理
                });
        };

        /**
         * 根据单年度查询数据
         */
        self.queryByYear = function(year, district, purpose, instrumentType, callback) {

            // 如果未选中年度，或者未选中区域，终止运行，避免提示框报错
            if (year.length === 0 || typeof(district.id) === 'undefined') {
                return;
            }

            // 拼接url
            var url = '/InstrumentCheckedRate/getAllCheckedRate/year/' + year + '/districtId/' + district.id;
            
            // 设置请求参数
            var params = {
                purposeId: purpose.id,                                     // 用途
                instrumentTypeId: instrumentType.id                        // 器具类别
            };

            // 发起请求
            $http.get(url, {params: params})
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);                           // 成功，回调
                    }
                }, function error(response) {
                    CommonService.httpError(response);                     // 失败，统一处理
                });
        };

        return {
            queryByYears: self.queryByYears,
            queryByYear: self.queryByYear
        };
    });
