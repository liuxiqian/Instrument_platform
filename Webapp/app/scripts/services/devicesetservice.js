'use strict';

/**
 * @ngdoc service
 * @name webappAppDeviceSetService
 * @description
 * # DeviceSetService  计量标准装置
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('DeviceSetService', ['$http', function ($http, CommonService) {
        var self = this;

        // 获取当前用户所在技术机构的计量标准装置
        self.getAllDeviceSetByTechnicalInstitutionId = function (technicalInstitutionId, callback) {
            // 请求路由
            var url = '/DeviceSet/getAllDeviceSetByTechnicalInstitutionId/' + technicalInstitutionId;

            $http.get(url)
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        return {
            getAllDeviceSetByTechnicalInstitutionId: self.getAllDeviceSetByTechnicalInstitutionId
        };
    }]);
