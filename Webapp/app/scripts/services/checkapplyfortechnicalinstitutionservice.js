'use strict';

/**
 * @ngdoc service
 * @name webappApp.checkApplyForTechnicalInstitutionService
 * @description
 * # checkApplyForTechnicalInstitutionService
 * 技术机构的检定申请
 */
angular.module('webappApp')
    .service('CheckApplyForTechnicalInstitutionService', function($http, CommonService) {
        var self = this;

        /**
         * 获取分页数据
         * @param    {[type]}                 params   查询条件
         * @param    {Function}               callback
         * @return   {[type]}
         * @author 梦云智 http://www.mengyunzhi.com
         * @DateTime 2017-11-22T13:33:28+0800
         */
        self.pageByParams = function (params, callback) {
            var url = '/MandatoryInstrumentCheckApply/pageOfCurrentUser';
            $http.get(url, {params: params})
            .then(function success(response){
                if (callback) {callback(response.data);}
            }, function error(response){
                CommonService.httpError(response);
            });
        };

        /**
         * 回复
         * @param    {[type]}                 id       申请id
         * @param    {[type]}                 data     回复数据
         * @param    {[type]}                 callbcak 回调函数
         * @return   {[type]}
         * @author 梦云智 http://www.mengyunzhi.com
         * @DateTime 2017-11-22T17:55:26+0800
         */
        self.reply = function(id, data, callback) {
            var url = '/MandatoryInstrumentCheckApply/audit/' + id;
            $http.patch(url, data)
            .then(function success(){
                if (callback) {callback();}
            }, function error(response){
                CommonService.httpError(response);
            });
        };

        self.getById = function(id, callback) {
            var url = '/MandatoryInstrumentCheckApply/' + id;
            $http.get(url)
                .then(function success(response) {
                    if (callback) { callback(response.data); }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        return {
            pageByParams: self.pageByParams,
            reply: self.reply,
            getById: self.getById
        };
    });
