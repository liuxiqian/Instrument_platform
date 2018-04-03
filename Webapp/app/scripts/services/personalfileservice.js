'use strict';

/**
 * @ngdoc service
 * @name webappApp.PersonalFileService
 * @description  资格证类别Service
 * # PersonalFileService
 * Service in the webappApp.
 * @author chenyuanyuan
 */
angular.module('webappApp')
	.service('PersonalFileService', ['$http', 'CommonService', function($http, CommonService) {
		var self = this;
        self.getPersonalFile = function (callback) {
            $http.get('/QualifierCertificateType/getAll').then(function successCallback(response) {
                callback(response.data);
            },function errorCallback(response) {
                CommonService.httpError(response);
            });
        };
        return{
            getPersonalFile:self.getPersonalFile
        };
    }]);
