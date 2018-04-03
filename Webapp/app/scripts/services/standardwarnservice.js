'use strict';

/**
 * @ngdoc service
 * @name webappApp.StandardWarnService
 * @description
 * # StandardWarnService
 * Service in the webappApp.
 * 标准器预警
 * zhuchenshu
 */
angular.module('webappApp')
  .service('StandardWarnService', function ($http, CommonService) {
  		var self = this;
  		/**
         * 获取当前登录用户的标准器预警列表
         * @param  {Function} callback      回调函数
         * @return {[array]}
         */
        self.pageAllWarnStandardDevice = function (params, callback) {
            var url = '/StandardDevice/pageAllWarnStandardDevice/';
  
            $http.get(url, {params: params})
                .then(function success(response) {
                    callback(response.data);

                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        return {
        	pageAllWarnStandardDevice : self.pageAllWarnStandardDevice
        };
  });
