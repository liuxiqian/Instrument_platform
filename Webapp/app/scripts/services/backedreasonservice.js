'use strict';

/**
 * @ngdoc service
 * @name webappApp.backedReasonService
 * @description 退回理由service
 * # backedReasonService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('backedReasonService', function ($http, CommonService) {
        var self = this;

        // 获取最近10条管理部门退回理由
        self.getTop10ManagementDepartmentBackedReasons = function (callback) {
            $http.get("/ManagementDepartmentBackedReason/getTop10ManagementDepartmentBackedReasons")
                .then(function success(response) {
                    if (callback) { callback(response.data); }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        /**
         * 获取最近10条技术机构退回原因
         * @param callback
         * @author chuhang
         */
        self.getTop10TechnicalInstitutionBackedReasons = function (callback) {
            $http.get("/TechnicalInstitutionBackedReason/getTop10TechnicalInstitutionBackedReasons")
                .then(function success(response) {
                    if (callback) { callback(response.data); }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        return {
            getTop10ManagementDepartmentBackedReasons: self.getTop10ManagementDepartmentBackedReasons,
            getTop10TechnicalInstitutionBackedReasons: self.getTop10TechnicalInstitutionBackedReasons
        };
    });
