'use strict';

/**
 * @ngdoc service
 * @name webappApp.MandatoryInstrumentApplyService
 * @description
 * # MandatoryInstrumentApplyService
 * 强制检定器具申请
 */
angular.module('webappApp')
    .service('MandatoryInstrumentApplyService', function($http, $window, config, UserServer, instrumentStatus, CommonService) {
        var self = this;
        self.baseUrl = '/MandatoryInstrumentApply';
        self.findById = function(id, callback) {
            var url = self.baseUrl + '/findById/' + id;
            $http.get(url)
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        self.save = function(workMandatoryInstrumentApply, callback) {
            var url = self.baseUrl + '/';
            $http.post(url, workMandatoryInstrumentApply)
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        /**
         * 计算某部门对某个强检申请上的所有强检器具是否具备检定能力
         * @param mandatoryInstrumentApplyId  强制检定申请ID
         * @param departmentId  部门id
         * @param callback
         * @return MandatoryInstrumentApply 强制检定申请
         */
        self.computeCheckAbilityByMandatoryInstrumentApplyIdAndDepartmentId = function(mandatoryInstrumentApplyId, departmentId, callback) {
            var url = self.baseUrl + '/computeCheckAbilityBy/MandatoryInstrumentApplyId/' +
                mandatoryInstrumentApplyId +
                '/DepartmentId/' + departmentId;
            $http.get(url)
                .then(function success(response) {
                    if (callback) {
                        var mandatoryInstrumentApply = response.data;
                        callback(mandatoryInstrumentApply);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        /**
         * 下载PDF报告
         * @param  {int}   applyId  [申请iD]
         * @param  {Function} callback
         * @return PDF文件
         * panjie
         */
        self.downloadPdfReportByApplyId = function(applyId, callback) {
            // 获取apply信息
            self.findById(applyId, function(apply) {
                // 按指定机构分组
                var checkDepartmentIds = [];
                angular.forEach(apply.mandatoryInstruments, function(mandatoryInstrument) {
                    if (mandatoryInstrument.status === instrumentStatus.normal.value) {
                        if (mandatoryInstrument.checkDepartment && mandatoryInstrument.checkDepartment.id) {
                            if (!checkDepartmentIds.includes(mandatoryInstrument.checkDepartment.id)) {
                                checkDepartmentIds.push(mandatoryInstrument.checkDepartment.id);
                            }
                        } else {
                            if (!checkDepartmentIds.includes(0)) {
                                checkDepartmentIds.push(0);
                            }
                        }
                    }
                });

                // 按分组分别导出备案报告
                if (checkDepartmentIds.length > 0) {
                    // 先获取一个临时的token, 然后要根据分别请求数据
                    UserServer.getTempTokenOfCurrentUser(function(token) {
                        angular.forEach(checkDepartmentIds, function(checkDepartmentId) {
                            var url = config.apiUrl + self.baseUrl;
                            if (checkDepartmentId === 0) {
                                // 未指定审核部门，生成送自治区的报表
                                url += '/generateDoNotHaveCheckAbilityPdfReportByApplyId/' + applyId;
                            } else {
                                // 指定了审核部门的，生成相应审核部门的报表
                                url += '/generatePdfReportByApplyId/' + applyId + '/checkDepartmentId/' + checkDepartmentId;
                            }
                            url += '/withToken?token=' + token;
                            $window.open(url, "_blank");
                        });
                        if (callback) { callback(); }
                    });
                } else {
                    if (callback) { callback(); }
                }
            });
        };

        self.uploadSingleAttachment = function(id, attachment, callback) {
            var url = "/MandatoryInstrumentApply/uploadSingleAttachment/" + id;
            // post文件类型的参数，使Content-Type:multipart/form-data;
            var formData = new FormData();
            formData.append('attachment', attachment._file);
            $http.post(url, formData, {
                    transformRequest: angular.identity,
                    headers: { 'Content-Type': undefined }
                })
                .then(function success(response) {
                    if (callback) {
                        callback(response.status, response.data.id);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });

        };

        // 更新
        self.update = function(id, data, callback) {
            var url = self.baseUrl + '/' + id;
            $http.patch(url, data)
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        /**
         * 获取带有检定机构是否有检定能力的强检申请列表
         * @param    {int}                     强检申请id
         * @param    {Function}               callback
         * @return   {object}                          强检申请
         * @author 梦云智 http://www.mengyunzhi.com
         * @DateTime 2017-10-16T15:18:24+0800
         */
        self.getMandatoryInstrumentApplyWithTechnicalInstitutionsAbilityByMandatoryInstrumentApplyId = function(id, callback) {
            var url = self.baseUrl + '/getMandatoryInstrumentApplyWithTechnicalInstitutionsAbilityByMandatoryInstrumentApplyId/' + id;
            $http.get(url)
                .then(function success(response) {
                    if (callback) { callback(response.data); }
                }, function(response) {
                    CommonService.httpError(response);
                });
        };

        // 一键指定
        self.assignToTechnicalInstitutionByMandatoryInstruments = function(mandatoryInstruments, callback) {
            var url = '/assignToTechnicalInstitutionByMandatoryInstruments';
            self.patchMandatoryInstrumentsToUrl(mandatoryInstruments, url, callback);
        };


        // 一键退回
        self.batchBackByMandatoryInstrumentsAndReasonAndApplyId = function(mandatoryInstruments, reason, applyId, callback) {
            var url = '/batchBack/' + applyId + '/' + reason;
            self.patchMandatoryInstrumentsToUrl(mandatoryInstruments, url, callback);
        };

        // 一键审核通过
        self.batchPassByMandatoryInstrumentsAndApplyId = function(mandatoryInstruments, applyId, callback) {
            var url = '/batchPass/' + applyId;
            self.patchMandatoryInstrumentsToUrl(mandatoryInstruments, url, callback);
        };

        /**
         * 将强检器具数组patch到某个url上以满足相关功能
         * @param  {array}  强检器具数组
         * @param  {string} 相对的URL
         * @param  {Function}
         * @return {}
         * @author panjie
         */
        self.patchMandatoryInstrumentsToUrl = function(mandatoryInstruments, relativeUrl, callback) {
            var url = self.baseUrl + relativeUrl;
            $http.patch(url, mandatoryInstruments)
                .then(function success(response) {
                    if (callback) { callback(response.status); }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        /**
         * 回复申请
         * @param opinions 回复意见
         * @param callback
         * @Author panjie
         */
        self.reply = function(apply, callback) {
            var url = self.baseUrl + '/reply/' + apply.id;
            $http.patch(url, apply)
                .then(function success(response) {
                    if (callback) { callback(response.status);}
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        return {
            findById: self.findById,
            save: self.save,
            computeCheckAbilityByMandatoryInstrumentApplyIdAndDepartmentId: self.computeCheckAbilityByMandatoryInstrumentApplyIdAndDepartmentId,
            downloadPdfReportByApplyId: self.downloadPdfReportByApplyId,
            uploadSingleAttachment: self.uploadSingleAttachment,
            update: self.update,
            getMandatoryInstrumentApplyWithTechnicalInstitutionsAbilityByMandatoryInstrumentApplyId: self.getMandatoryInstrumentApplyWithTechnicalInstitutionsAbilityByMandatoryInstrumentApplyId,
            assignToTechnicalInstitutionByMandatoryInstruments: self.assignToTechnicalInstitutionByMandatoryInstruments,
            batchBackByMandatoryInstrumentsAndReasonAndApplyId: self.batchBackByMandatoryInstrumentsAndReasonAndApplyId,
            batchPassByMandatoryInstrumentsAndApplyId: self.batchPassByMandatoryInstrumentsAndApplyId,
            reply: self.reply
        };
    });
