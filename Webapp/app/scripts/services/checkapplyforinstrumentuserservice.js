'use strict';

/**
 * @ngdoc service
 * @name webappApp.checkApplyForInstrumentUserService
 * @description
 * # checkApplyForInstrumentUserService
 * 强检器具检定管理-检定申请  适用于器具用户
 */
angular.module('webappApp')
    .service('checkApplyForInstrumentUserService', function($http, CommonService, UserServer, $window) {
        var self = this;

        self.data = {};

        self.getById = function(id, callback) {
            var url = '/MandatoryInstrumentCheckApply/' + id;
            $http.get(url)
                .then(function success(response) {
                    if (callback) { callback(response.data); }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        // 新增数据
        self.save = function(data) {
            var url = '/MandatoryInstrumentCheckApply';
            return $http.post(url, data);
        };

        /**
         * 获取当前器具用户的分页数据
         * @param    {[type]}                 params   参数
         * @param    {Function}               callback 回调
         * @return   {[type]}
         * @author 梦云智 http://www.mengyunzhi.com
         * @DateTime 2017-11-22T19:54:36+0800
         */
        self.pageByParams = function(params, callback) {
            var url = '/MandatoryInstrumentCheckApply/pageOfCurrentUser';
            $http.get(url, {params: params})
                .then(function success(
                    response) {
                    if (callback) {callback(response.data);}
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        /**
         * 根据检定申请id下载检定申请方法
         * @param  {[type]} checkApplyId 检定申请id
         * @author zhangxishuo
         */
        self.downloadPdfByCheckApplyId = function(checkApplyId) {
            // 调用UserServer中获取当前用户token的方法，传入回调函数
            UserServer.getTempTokenOfCurrentUser(function(token) {
                // 拼接url
                var url = CommonService.getBashUrl() + "/MandatoryInstrumentCheckApply/generatePdfApply/" + checkApplyId + "/withToken?token=" + token;
                // 新标签页打开，下载文件
                $window.open(url, "_blank");
            });
        };


        return {
            getById: self.getById,
            save: self.save,
            pageByParams: self.pageByParams,
            downloadPdfByCheckApplyId: self.downloadPdfByCheckApplyId
        };
        // AngularJS will instantiate a singleton by calling "new" on this function
    });
