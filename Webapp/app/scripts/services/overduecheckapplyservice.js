'use strict';

/**
 * @ngdoc service
 * @name webappApp.OverdueCheckApplyService
 * @description
 * # OverdueCheckApplyService
 * 超期未检器具 检定申请
 */
angular.module('webappApp')
    .service('OverdueCheckApplyService', function ($http, CommonService) {
        var self = this;

        self.pageOfCurrentUser = function (params) {
            var url = '/OverdueCheckApply/pageOfCurrentUser';
            return $http.get(url, {params: params});
        };

        /**
         * 回复
         * @param  {[type]}   work     工作
         * @param  {Function} callback
         * @return {}
         * panjie
         */
        self.reply = function (work, callback) {
            var url = '/OverdueCheckApply/';
            var object = {
                work: work,
                apply: work.apply
            };

            $http.patch(url, object)
                .then(function success(response) {
                    if (callback) {
                        callback(response.status);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        self.save = function (data, callback) {
            var url = '/OverdueCheckApply/';
            $http.post(url, data)
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        return {
            pageOfCurrentUser: self.pageOfCurrentUser,
            reply: self.reply,
            save: self.save
        };
    });
