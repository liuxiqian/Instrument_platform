'use strict';

/**
 * @ngdoc service
 * @name webappApp.InstrumentTypeFirstLevelService
 * @description
 * # 一级器具类别
 * Service in the webappApp.
 */
angular.module('webappApp')
.service('InstrumentFirstLevelTypeService', function ($http, CommonService) {
	var self = this;
	self.getAllByDisciplineId = function (disciplineId, callback) {
		var url = '/InstrumentFirstLevelType/getAllByDisciplineId/' + disciplineId;
		$http.get(url)
		.then(function success(response) {
			if (callback) {
				callback(response.data);
			}
		}, function error(response) {
            CommonService.httpError(response);
		});
	};

	self.save = function(data, callback) {
		var url = '/InstrumentFirstLevelType/';
		$http.post(url, data)
		.then(function success(response){
			if (callback) {callback(response.data);}
		}, function error(response){
            CommonService.httpError(response);
		});
	};

    self.delete = function(id, callback) {
        $http.delete('/InstrumentFirstLevelType/delete/' + id)
            .then(function success(response) {
                if (callback) {
                    callback(response);
                }
            }, function error(response) {
                CommonService.httpError(response);
            });
    };

    /**
     * 更新
     * @param id     实体ID
     * @param data   数据
     * @param callback
     * @author chenyuanyuan
     */
    self.update = function (id, data, callback) {
        $http.put('/InstrumentFirstLevelType/update/' + id, data)
            .then(function success(response) {
                if (callback) {
                    callback(response.status);
                }
            }, function error(response) {
                CommonService.httpError(response);
            });
    };

    self.getOneByManageFirstLevelId = function (id, callback) {
        $http.get('/InstrumentFirstLevelType/get/' + id)
            .then(function success(response) {
                if (callback) {
                    callback(response.data);
                }
            }, function error(response) {
                CommonService.httpError(response);
            });
    };

	return {
		getAllByDisciplineId: self.getAllByDisciplineId,
		save: self.save,
        delete: self.delete,
        update: self.update,
        getOneByManageFirstLevelId: self.getOneByManageFirstLevelId
	};
});
