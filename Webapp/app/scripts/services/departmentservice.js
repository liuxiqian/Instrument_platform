'use strict';

/**
 * @ngdoc service
 * @name webappApp.departmentService
 * @description 部门 包括器具用户，管理部门，生产企业，技术机构
 * # departmentService
 * Service in the webappApp.
 */
angular.module('webappApp')
.service('departmentService', ['$http', 'CommonService', function ($http, CommonService) {
	var self = this;
	/**
	 * 通过工作流ID获取全部的可选部门信息
	 * @param workflowNodeId
	 * @param callback
	 * @author panjie
	 */
	self.getAllByWorkflowNodeId = function (workflowNodeId, callback) {
		var url;
		url = '/Department/getAllByWorkflowNodeIdOfCurrentLoginUser/' + workflowNodeId;
		$http.get(url)
		.then(function success(response) {
			if (callback) {
				callback(response.data);
			}
		}, function error(response) {
            CommonService.httpError(response);
		});
	};

	/**
	 * 通过工作流获取全部的可选部门信息
	 * @param workflowNodeId
	 * @param callback
	 * @author panjie
	 */
	self.getAllByWorkflowNode = function (workflowNode, callback) {
		self.getAllByWorkflowNodeId(workflowNode.id, callback);
	};


	// 利用$http进行数据传输
	self.getAll = function (callback) {
		$http.get('/data/department/getAllArray.json').then(function successCallback(response) {
			callback(response.data);
		}, function errorCallback() {

		});
	};

	// 获取带有 是否具备某种器具检定能力 字段的部门列表
	self.getAllWithCheckAbilityByWorkflowNodeAndMandatoryInstrument = function (workflowNode, mandatoryInstrument, callback) {
		return self.getAllWithCheckAbilityByWorkflowNodeIdAndMandatoryInstrumentId(workflowNode.id, mandatoryInstrument.id, callback);
	};

	// 获取带有 是否具备某种器具检定能力 字段的部门列表
	self.getAllWithCheckAbilityByWorkflowNodeIdAndMandatoryInstrumentId = function (workflowNodeId, mandatoryInstrumentId, callback) {
		var url = '/Department/getAllWithCheckAbilityByWorkflowNodeIdAndMandatoryInstrumentIdOfCurrentLoginUser/workflowNodeId/' + workflowNodeId + '/mandatoryInstrumentId/' + mandatoryInstrumentId;
		$http.get(url)
		.then(function success(response) {
			if (callback) {
				callback(response.data);
			}
		}, function error(response) {
            CommonService.httpError(response);
		});
	};

	// 获取本区域上的所有技术机构
	self.getAllTechnicalInstitutionsByDistrictId = function (districtId, callback) {
		var url = '/Department/getAllTechnicalInstitutionsByDistrictId/' + districtId;
		$http.get(url)
		.then(function success(response) {
			if (callback) {
				callback(response.data);
			}
		}, function error(response) {
            CommonService.httpError(response);
		});
	};

	// 获取当前用户所辖区域内的所有的某种部门类型(比如器具用户)的列表
	self.pageByDepartmentTypePinyinOfCurrentUserManageDistricts = function (departmentTypePinyin, params, callback) {
		var url = '/Department/pageByDepartmentTypePinyinOfCurrentLoginUserManageDistricts/' + departmentTypePinyin;
		$http.get(url, {params: params})
		.then(function success(response) {
			if (callback) {
				callback(response.data);
			}
		}, function error(response) {
            CommonService.httpError(response);
		});
	};

	//删除功能
	self.delete = function (id, callback) {
		$http.delete('/Department/delete/' + id)
		.then(function success(response) {
			if (callback) {
				callback(response);
			}
		}, function error(response) {
            CommonService.httpError(response);
		});
	};

	// 获取某个区域上的所有的器具用户
	self.getAllInstrumentUserByDistrictId = function(districtId, callback) {
		var url = '/Department/getAllInstrumentUserByDistrictId/' + districtId;
		$http.get(url)
		.then(function success(response){
			if (callback) {callback(response.data);}
		}, function error(response){
            CommonService.httpError(response);
		});
	};

	//更新部门信息
    self.update = function (object, callback) {
        var url = '/Department/';
        //处理传过来的数据
        var data = object;
        data.departmentType = {id: data.departmentType.id};
        data.district = {id: data.district.id};

        $http.put(url + object.id, data)
            .then(function success(response) {
                callback(response);
            }, function error (response) {
                CommonService.httpError(response);
            });
    };

    //获取某个用户信息
    self.getOneDepartmentOfId = function (departmentId, callback) {
        var url = "/Department/" + departmentId;

        $http.get(url)
            .then(function success(response) {
                callback(response.data);
            }, function success(response) {
                CommonService.httpError(response);
            });
    };

	return {
		getAll: self.getAll,
		getAllByWorkflowNode: self.getAllByWorkflowNode,
		getAllByWorkflowNodeId: self.getAllByWorkflowNodeId,
		getAllTechnicalInstitutionsByDistrictId: self.getAllTechnicalInstitutionsByDistrictId,
		getAllWithCheckAbilityByWorkflowNodeAndMandatoryInstrument: self.getAllWithCheckAbilityByWorkflowNodeAndMandatoryInstrument,
		getAllWithCheckAbilityByWorkflowNodeIdAndMandatoryInstrumentId: self.getAllWithCheckAbilityByWorkflowNodeIdAndMandatoryInstrumentId,
		pageByDepartmentTypePinyinOfCurrentUserManageDistricts: self.pageByDepartmentTypePinyinOfCurrentUserManageDistricts,
		delete: self.delete,
		getAllInstrumentUserByDistrictId: self.getAllInstrumentUserByDistrictId,
        update: self.update,
        getOneDepartmentOfId: self.getOneDepartmentOfId,
        getById: self.getOneDepartmentOfId
	};
}]);
