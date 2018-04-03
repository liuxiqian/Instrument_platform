'use strict';

/**
 * @ngdoc service
 * @name webappApp.InstrumentCheckInfoService
 * @description
 * # InstrumentCheckInfoService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('InstrumentCheckInfoService', ['CommonService', '$http', function (CommonService, $http) {
        var self = this;

        //处理保存的数据
        self.dealSaveData = function (value, type) {
            var data = value;

            data.ratifierQualifier = {id: value.ratifierQualifier.id};
            data.verifierQualifier = {id: value.verifierQualifier.id};
            data.inspectorQualifier = {id: value.inspectorQualifier.id};
            data.checkResult = {id: value.checkResult.id};
            data.mandatoryInstrument = {id: value.mandatoryInstrument.id};
            if (type === 'add') {
                data.instrumentCertificateType = {id: value.instrumentCertificateType.selected.id};
                data.certificateStatus = {id: value.certificateStatus.selected.id};
            } else {
                data.instrumentCertificateType = {id: value.instrumentCertificateType.id};
                data.certificateStatus = {id: value.certificateStatus.id};
                data.checkDepartment = {id: value.checkDepartment.id};
            }

            return data;
        };

        //获取当前登录用户的所有强检定信息
        self.pageAllOfCurrentUser = function (params, callback) {
            var queryString = CommonService.querySerialize(params);
            var pageAllOfCurrentUserUrl = '/InstrumentCheckInfo/pageAllOfCurrentUser';
            $http.get(pageAllOfCurrentUserUrl + '?' + queryString)
                .then(function success(response) {
                    callback(response.data);
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        //获取所有的器具证书类型
        self.getAllInstrumentCertificateTypes = function (callback) {
            var getAllInstrumentCertificateTypesUrl = '/InstrumentCertificateType/getAll';

            $http.get(getAllInstrumentCertificateTypesUrl)
                .then(function success(response) {
                    callback(response.data);
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        //获取所有的证书状态
        self.getAllCertificateStatuses = function (callback) {
            var getAllCertificateStatus = '/CertificateStatus/getAll';

            $http.get(getAllCertificateStatus)
                .then(function success(response) {
                    callback(response.data);
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        //保存方法
        self.save = function (data, callback) {
            var saveUrl = '/InstrumentCheckInfo/save';
            //处理需要保存的数据
            var sendData = self.dealSaveData(data, 'add');

            $http.post(saveUrl, sendData)
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        //保存但是对保存的参数不处理
        self.saveUnDealSaveData = function (data, callback) {
            var saveUrl = '/InstrumentCheckInfo/save';
            $http.post(saveUrl, data)
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        //通过强检器具使用信息获取所有的检定信息
        self.getAllByMandatoryInstrumentId = function (mandatoryInstrumentId, callback) {
            var getAllByMandatoryInstrumentUrl = '/InstrumentCheckInfo/pageAllByMandatoryInstrumentId/' + mandatoryInstrumentId;

            $http.get(getAllByMandatoryInstrumentUrl)
                .then(function success(response) {
                    callback(response.data);
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        //更新检定信息
        self.update = function (instrumentCheckInfoId, instrumentCheckInfo, callback) {
            var url = '/InstrumentCheckInfo/';

            //处理传过来的数据
            var sendData = self.dealSaveData(instrumentCheckInfo, 'edit');

            $http.put(url + instrumentCheckInfoId, sendData)
                .then(function success(response) {
                    if (callback) {
                        callback(response.status);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        //删除方法
        self.delete = function (instrumentCheckInfoId, successCallback) {
            var deleteUrl = '/InstrumentCheckInfo/delete/';

            $http.delete(deleteUrl + instrumentCheckInfoId)
                .then(function success() {
                    successCallback();
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        // 根据查询条件获取管理部门的强检器具鉴定信息
        self.pageAllOfManagementDepartmentBySpecification = function (params, callback) {
            var url = '/InstrumentCheckInfo/pageAllOfManagementDepartmentBySpecification';
            $http.get(url, {params: params})
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        // 根据查询条件获取器具用户下的强检器具鉴定信息
        self.pageAllOfMeasureUserBySpecification = function (params, callback) {
            var url = '/InstrumentCheckInfo/pageAllOfMeasureUserBySpecification';
            $http.get(url, {params: params})
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        // 根据查询条件获取技术机构的强检器具鉴定信息
        self.pageAllOfTechnicalInstitutionDepartmentBySpecification = function (params, callback) {
            var url = '/InstrumentCheckInfo/pageAllOfTechnicalInstitutionDepartmentBySpecification';
            $http.get(url, {params: params})
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        //根据强检器具id获取强检器具实体
        self.getOneByMandatoryInstrumentId = function (id, callback) {
            var url = '/MandatoryInstrument/';
            $http.get(url + id)
                .then(function success(response) {
                    callback(response.data);

                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        // 器具用户保存器具
        self.saveViaInstrumentUserDepartment = function (data, callback) {
            var url = '/InstrumentCheckInfo/saveViaApplyInstrumentUserDepartment';
            $http.post(url, data)
                .then(function success(response) {
                    if (callback) {
                        callback(response);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        //返回所有的方法
        return {
            pageAllOfCurrentUser: self.pageAllOfCurrentUser,
            getAllInstrumentCertificateTypes: self.getAllInstrumentCertificateTypes,
            getAllCertificateStatuses: self.getAllCertificateStatuses,
            save: self.save,
            saveViaInstrumentUserDepartment: self.saveViaInstrumentUserDepartment,
            getAllByMandatoryInstrumentId: self.getAllByMandatoryInstrumentId,
            update: self.update,
            delete: self.delete,
            pageAllOfManagementDepartmentBySpecification: self.pageAllOfManagementDepartmentBySpecification,
            pageAllOfTechnicalInstitutionDepartmentBySpecification: self.pageAllOfTechnicalInstitutionDepartmentBySpecification,
            pageAllOfMeasureUserBySpecification: self.pageAllOfMeasureUserBySpecification,
            getOneByMandatoryInstrumentId: self.getOneByMandatoryInstrumentId,
            saveUnDealSaveData: self.saveUnDealSaveData
        };
    }]);
