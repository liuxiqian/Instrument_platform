'use strict';

/**
 * @ngdoc service
 * @name webappApp.InstrumentTypeService
 * @description
 * @Author panjie
 * 器具类别 服务层
 */
angular.module('webappApp')
    .service('InstrumentTypeService', function($http, CommonService) {
        var self = this;
        self.class = {name: 'InstrumentTypeService'};

        self.currentOperateObject = {}; //当前操作的对象
        self.save = function(data, type, callback) {
            var url = self.getPreUrlByType(type);
            url += 'InstrumentType/save';
            $http.post(url, data)
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        /**
         * 更新
         * @param id 实体id
         * @param data 数据
         * @param callback
         * @author panjie
         */
        self.update = function(id, data, type, callback) {
            var url = self.getPreUrlByType(type);
            url += 'InstrumentType/update/' + id;

            $http.put(url, data)
                .then(function success(response) {
                    if (callback) {
                        callback(response.status);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        // 获取所有的标准器类别
        self.getAllByInstrumentFirstLevelTypeIdAndType = function(instrumentFirstLevelTypeId, type, callback) {
            $http.get('/StandardDeviceInstrumentType/getAllByInstrumentFirstLevelTypeId/' + instrumentFirstLevelTypeId)
                .then(function success(response) {
                    callback(response.data);
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        /**
         * 根据学科类别ID获取列表
         * @param id
         * @param callback
         * @author panjie
         */
        self.getAllByDisciplineId = function(disciplineId, callback) {
            $http.get('/InstrumentType/allByDisciplineId/' + disciplineId)
                .then(function success(response) {
                    callback(response.data);
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        /**
         * 根据学科类别ID获取分页列表
         * @param id
         * @param callback
         * @author panjie
         */
        self.pageByDisciplineId = function(disciplineId, params, type, callback) {
            var queryString = CommonService.querySerialize(params);
            var url = self.getPreUrlByType(type) + 'InstrumentType/pageByDisciplineId/' + disciplineId + '?' + queryString;
            $http.get(url)
                .then(function success(response) {
                    callback(response.data);
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        self.getPreUrlByType = function(type) {
            var url;
            if (type === 'all') {
                // 全部数据
                url = '/';
            } else if (type === 'MandatoryInstrument') {
                // 强制检定数据
                url = '/Mandatory';
            } else if (type === 'standardDeviceInstrument') {
                // 强制检定数据
                url = '/StandardDevice';
            } else {
                alert('InstrumentTypeService.getPreUrlByType -> 传入的type值:' + type + '不符合规范');
            }
            return url;
        };

        /**
         * 根据测量范围获取对应的器具类别
         * @param measureScaleId
         * @param callback
         */
        self.getByMeasureScaleId = function(measureScaleId, callback) {
            $http.get('/InstrumentType/getByMeasureScaleId/' + measureScaleId)
                .then(function success(response) {
                    callback(response.data);
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        self.delete = function(id, callback) {
            $http.delete('/InstrumentType/delete/' + id)
                .then(function success(response) {
                    if (callback) {
                        callback(response);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        // 获取一级类别下的器具类别
        self.getAllInstrumentTypeByInstrumentFirstLevelTypeId = function(id, callback) {
            $http.get('/MandatoryInstrumentType/getAllByInstrumentFirstLevelTypeId/' + id)
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        // 获取某条数据信息
        self.get = function(id, callback) {
        	var url = '/MandatoryInstrumentType/' + id;
        	$http.get(url)
        	.then(function success(response){
        		if (callback) {
        			callback(response);
        		}
        	}, function error(response) {
                CommonService.httpError(response);
        	});
        };

        return {
            get: self.get,
            save: self.save,
            delete: self.delete,
            update: self.update,
            pageByDisciplineId: self.pageByDisciplineId,
            getByMeasureScaleId: self.getByMeasureScaleId,
            getAllByDisciplineId: self.getAllByDisciplineId,
            getAllByInstrumentFirstLevelTypeIdAndType: self.getAllByInstrumentFirstLevelTypeIdAndType,
            getAllInstrumentTypeByInstrumentFirstLevelTypeId: self.getAllInstrumentTypeByInstrumentFirstLevelTypeId
        };
    });
