'use strict';

/**
 * @ngdoc service
 * @name webappApp.StandardAlarmService
 * @description
 * # StandardAlarmService
 * Service in the webappApp.
 * zhuchenshu
 * 标准器报警
 */
angular.module('webappApp')
  .service('StandardAlarmService', function ($http, CommonService) {
        var self = this;
  		  /**
         * 获取当前登录用户的标准器报警列表
         * @param  {Function} callback      回调函数
         * @return {[array]}
         */
        self.pageAllAlarmStandardDevice = function (params, callback) {
            var url = '/StandardDevice/pageAllAlarmStandardDevice/';
  
            $http.get(url, {params: params})
                .then(function success(response) {
                    callback(response.data);

                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        return {
        	pageAllAlarmStandardDevice : self.pageAllAlarmStandardDevice
        };
  });
