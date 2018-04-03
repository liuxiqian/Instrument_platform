'use strict';

/**
 * @ngdoc service
 * @name webappApp.departmentTypeService
 * @description     部门类型  指令
 * # departmentTypeService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('departmentTypeService', ['$http', function ($http) {
        var self = this;

        self.departmentTypeArray  = [];         //部门类型数组
        self.instrumentuser       = {};         //器具用户部门类型
        self.technicalInstitution = {};         //技术机构部门类型
        self.enterprise           = {};         //生产企业部门类型
        self.management           = {};         //管理部门部门类型
        /**
         * 获取所有的部门类型
         * @param  {Function} callback [description]
         * @return {[type]}            [description]
         */
        self.getCurrentUserdepartmentTypeArray = function(callback) {
            if (self.departmentTypeArray.length !== 0) {
                callback(self.departmentTypeArray);

            } else {
                $http.get('/DepartmentType/').then(function(response) {
                    self.departmentTypeArray = response.data;
                    self.dealWithDepartmentTypeArray();
                    callback(response.data);
                });
            }
        };

        /**
         * 获取器具用户部门类型实体
         * @param  {Function} callback [description]
         * @return {[type]}            [description]
         */
        self.getDepartmentTypeOfInstrumentuser = function(callback) {
            if (!angular.equals(self.instrumentuser, {})) {
                callback(self.instrumentuser);

            } else {

                $http.get('/DepartmentType/').then(function(response) {
                    self.departmentTypeArray = response.data;
                    self.dealWithDepartmentTypeArray();
                    callback(self.instrumentuser);
                });
            }
        };

        /**
         * 获取生产企业部门类型实体
         * @param  {Function} callback [description]
         * @return {[type]}            [description]
         */
        self.getDepartmentTypeOfEnterprise = function(callback) {
            if (!angular.equals(self.enterprise, {})) {
                callback(self.enterprise);

            } else {

                $http.get('/DepartmentType/').then(function(response) {
                    self.departmentTypeArray = response.data;
                    self.dealWithDepartmentTypeArray();
                    callback(self.enterprise);
                });
            }
        };

        /**
         * 获取管理部门部门类型实体
         * @param  {Function} callback [description]
         * @return {[type]}            [description]
         */
        self.getDepartmentTypeOfManagement = function(callback) {
            if (!angular.equals(self.management, {})) {
                callback(self.management);

            } else {

                $http.get('/DepartmentType/').then(function(response) {
                    self.departmentTypeArray = response.data;
                    self.dealWithDepartmentTypeArray();
                    callback(self.management);
                });
            }
        };

        /**
         * 获取技术机构部门类型实体
         * @param  {Function} callback [description]
         * @return {[type]}            [description]
         */
        self.getDepartmentTypeOfTechnicalInstitution = function(callback) {
            if (!angular.equals(self.technicalInstitution, {})) {
                callback(self.technicalInstitution);

            } else {

                $http.get('/DepartmentType/').then(function(response) {
                    self.departmentTypeArray = response.data;

                    // 统一处理几种类型后，再次调用本函数
                    self.dealWithDepartmentTypeArray();
                    self.getDepartmentTypeOfTechnicalInstitution(callback);
                });
            }
        };

        /**
         * 处理传回来的部门类型数组，赋值给相应的部门类型
         * @return {[type]} [description]
         */
        self.dealWithDepartmentTypeArray = function() {
            angular.forEach(self.departmentTypeArray, function(value) {
                switch(value.pinyin) {
                    case 'qijuyonghu':
                        self.instrumentuser = value;
                        break;
                    case 'shengchanqiye':
                        self.enterprise = value;
                        break;
                    case 'jishujigou':
                        self.technicalInstitution = value;
                        break;
                    case 'guanlibumen':
                        self.management = value;
                        break;
                    default:
                        break;
                }
            });
        };

        return {
            getCurrentUserdepartmentTypeArray: self.getCurrentUserdepartmentTypeArray,
            getDepartmentTypeOfEnterprise: self.getDepartmentTypeOfEnterprise,
            getDepartmentTypeOfInstrumentuser: self.getDepartmentTypeOfInstrumentuser,
            getDepartmentTypeOfTechnicalInstitution: self.getDepartmentTypeOfTechnicalInstitution,
            getDepartmentTypeOfManagement: self.getDepartmentTypeOfManagement
        };
    }]);
