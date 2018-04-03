'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:InstrumenttypeaddCtrl
 * @description
 * # 器具(强检器具 标准器)类别添加
 * @author panjie
 */
angular.module('webappApp')
.controller('InstrumentTypeAddCtrl', function ($scope, $uibModal, AccuracyService, CommonService, InstrumentTypeService, PurposeService, $stateParams, InstrumentFirstLevelTypeService) {
	var self = this;
	$scope.config = self.config = $stateParams.config;
	// 根据传入的参数获取当前用户选择类别
	$scope.type = self.type = $stateParams.type; // 类型：add:添加; edit:编辑

	self.init = function () {
		PurposeService.all(function (data) {     // 可选用途
			$scope.purposes = data;
		});
	};
	self.init();

	// 为新增界面时，初始化的代码
	self.addInit = function () {
		self.disciplineId = parseInt($stateParams.disciplineId ? $stateParams.disciplineId : 0); // 学科类别id
		$scope.showExtendInfo = true;          // 显示扩展信息
		$scope.data = {};
		$scope.data.name = '';                  // 名称
		$scope.data.pinyin = '';                // 拼音
		$scope.discipline = {id: self.disciplineId};        // 对应的学科类别
		$scope.discipline.accuracies = []; // 学科类别对应的精度
		$scope.data.accuracyDisplayName = {};   // 精度显示名称
		$scope.data.instrumentFirstLevelType = {};  // 一级学科类别
		$scope.isAdd = false;					//不显示“保存并新建”按钮
		PurposeService.all(function (data) {     // 可选用途
			$scope.purposes = data;
		});
		self.initAboutDiscipline();
	};

	// 学科变化后对应的初始化
	self.initAboutDiscipline = function () {
		self.init();
		$scope.data.accuracies = [];            // 已选精度
		$scope.data.measureScales = [];         // 已选测试量范
		$scope.data.specifications = [];        // 已选规格型号
		$scope.data.purposes = [];              // 用途
	};

	// 初始化当前添加的实体类型 -- 用于进行弹窗添加实体
	self.initCurrentAddEntityType = function () {
		self.currentAddEntityType = '';         // 当前添加的实体类型
	};


	// 弹出窗口初始化

    self.modalInstance = function () {
		$uibModal.open({
			templateUrl: 'views/system/instrumentType/addEntity.html',
			size: 'small',
			controller: 'InstrumentTypeAddCtrlModalInstanceCtrl',
			scope: $scope
		});
	};

	// 添加实体
	self.addEntity = function (type) {
		self.currentAddEntityType = type;
		self.modalInstance();
	};

	// 保存实体
	self.saveEntity = function (object, callback) {
		var currentAddEntityType = self.currentAddEntityType;
		// 精度
		if (currentAddEntityType === 'accuracy') {
			self.saveAccuarcy(object, function () {
				callback();
			});
		} else if (currentAddEntityType === 'measureScale') { // 测量范围
			self.saveMeasureScale(object, callback);
		} else if (currentAddEntityType === 'specification') { // 规格型号
			self.saveSpecification(object, callback);
		} else if (currentAddEntityType === 'instrumentFirstLevelType') {
			self.saveInstrumentFirstLevelType(object, callback);
		}
		self.initCurrentAddEntityType();
	};

	// 添加测量范围
	self.saveMeasureScale = function (object, callback) {
		$scope.data.measureScales.push({value: object.name, pinyin: object.pinyin, weight:$scope.data.measureScales.length});
		callback();
	};

	// 添加规格型号
	self.saveSpecification = function (object, callback) {
		$scope.data.specifications.push({value: object.name, pinyin: object.pinyin, weight:$scope.data.specifications.length});
		callback();
	};

	// 新增精度
	self.saveAccuarcy = function (object, callback) {
		$scope.data.accuracies.push({value: object.name, pinyin: object.pinyin, weight:$scope.data.accuracies.length});
		callback();
	};

	// 新增分类一级名称
	self.saveInstrumentFirstLevelType = function (object, callback) {
		// 先存一学科类别，再重置为0，数据获取后来恢复学科类别。目的是为了触发分类一级名称指类重新请求最新的数据
		var discipline = $scope.discipline;
		$scope.discipline = {id: 0};
		var data = {
			name: object.name,
			pinyin: object.pinyin,
			discipline: {id: discipline.id}
		};
		InstrumentFirstLevelTypeService.save(data, function (response) {
			$scope.discipline = discipline;
			if (callback) {
				callback(response);
			}
		});
	};

	// 删除一个规格型号
	self.popSpecificationsByIndex = function (value) {
		self.popListByListAndDateValue($scope.data.specifications, value);
	};

	// 删除一个测试范围
	self.popMeasureScalesByIndex = function (value) {
		self.popListByListAndDateValue($scope.data.measureScales, value);
	};

	// 删除一个精确度
	self.popAccuraciesByIndex = function (index) {
		self.popListByListAndDateValue($scope.data.accuracies, index);
	};

	// 上浮测量范围
	self.upMeasureScale = function(MeasureScale) {
		self.upObjectByListAndObject($scope.data.measureScales, MeasureScale);
	};

	// 下沉测量范围
	self.downMeasureScale = function(MeasureScale) {
		self.downObjectByListAndObject($scope.data.measureScales, MeasureScale);
	};

	// 上浮精度
	self.upAccuracy = function(accuracy) {
		self.upObjectByListAndObject($scope.data.accuracies, accuracy);
	};

	// 下沉精度
	self.downAccuracy = function(accuracy) {
		self.downObjectByListAndObject($scope.data.accuracies, accuracy);
	};

	// 上浮可选规格型号
    self.upSpecification = function (specification) {
        self.upObjectByListAndObject($scope.data.specifications, specification);
    };

    // 下沉可选规格型号
    self.downSpecification = function(specification) {
        self.downObjectByListAndObject($scope.data.specifications, specification);
    };

	// 将对象由原数组中，上浮一个
	self.upObjectByListAndObject = function(list, object) {
		if (object.weight > 0) {
				angular.forEach(list, function(value) {
				if (value.weight === object.weight - 1) {
					value.weight++;
				}
			});
			object.weight--;
		}
	};

	// 将对象由原数组中，下沉一个
	self.downObjectByListAndObject = function(list, object) {
		if (object.weight < list.length - 1) {
				angular.forEach(list, function(value) {
				if (value.weight === object.weight + 1) {
					value.weight--;
				}
			});
			object.weight++;
		}
	};

	// 从数组中删除一个元素，并重新设置权限
	self.popListByListAndIndex = function(list, index) {
		var object = list[index];
		list.splice(index, 1);
		angular.forEach(list, function(value){
			if (value.weight > object.weight) {
				value.weight--;
			}
		});
	};

	// //从数组中删除一个元素，并重新设置权限
	self.popListByListAndDateValue = function(list, value) {
		var object = {};
		angular.forEach(list, function(data, key) {
			if (data.value === value) {		//如果value值相等就删除
				object = list[key];
				list.splice(key, 1);
			}
		});

		angular.forEach(list, function(value){
			if (value.weight > object.weight) {
				value.weight--;
			}
		});
	};

	// 选中/反选 精度
	self.toggleAccuracy = function (accuracy) {
		CommonService.toggleCheckbox(accuracy, $scope.data.accuracies);
	};

	// 选中/反选 用途
	self.togglePurpose = function (purpose) {
		CommonService.toggleCheckbox(purpose, $scope.data.purposes);
	};

	// 保存/更新
	self.save = function (callback) {
		if ($scope.isEdit) {
			InstrumentTypeService.update($scope.data.id, $scope.data, self.config.type, callback);
		} else {
			InstrumentTypeService.save($scope.data, self.config.type, callback);
		}
	};

	// 保存并新建
	self.saveAndNew = function () {
		$scope.submiting = true;
		self.save(function () {
			CommonService.success();
		});
	};

	// 保存并关闭
	self.saveAndClose = function () {
		$scope.form.submitted = true;   // 设置提交
		if (self.isAccuraciesError() || self.isMeasureScalesError() || self.isAccuracyDisplayNameError()) {
		} else {
			$scope.submiting = true;
			self.save(function () {
				CommonService.success();
			});
		}
	};

	// 判断精度是否被默认选中
	self.isAccuracyChecked = function (accuracy) {
		if ($scope.data) {
			var index = CommonService.searchByIndexName(accuracy, 'id', $scope.data.accuracies);
			if (index === -1) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	};

	// 判断用途是否被默认选中
	self.isPurposeChecked = function (purpose) {
		if ($scope.data) {
			var index = CommonService.searchByIndexName(purpose, 'id', $scope.data.purposes);
			if (index === -1) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	};


	// 按类型为add或edit分别进行数据的初始化
	if (angular.equals(self.type, 'add')) {
		self.addInit();
		$scope.isEdit = false;
		$scope.isAdd = true;
		// 监听学科类别是否发生变化
		$scope.$watch('data.instrumentFirstLevelType', function (newValue) {
			if (newValue && newValue.id) {
				$scope.showExtendInfo = true;
			} else {
				$scope.showExtendInfo = false;
			}
			self.initAboutDiscipline();
		});
	} else {
		$scope.isEdit = true;
		var id = $stateParams.id;
		if (id === "") {
			CommonService.error('未接到收到ID值');
			return;
		} else {
			InstrumentTypeService.get(id, function(response) {
				if (response.status === 200) {
					$scope.data = response.data;
					$scope.discipline = $scope.data.instrumentFirstLevelType.discipline;
					$scope.showExtendInfo = true;
					$scope.isAdd = false;
				} else {
					CommonService.error('数据请求发生错误，请稍后重试');
				}
			});
		}
	}

	// 是否显示添加一级器具类别
	self.showAddInstrumentTypeFirstLevel = function () {
		if ($scope.discipline && $scope.discipline.id && $scope.isAdd) {
			return true;
		} else {
			return false;
		}
	};

	// 是否添加了精确度
	self.isAccuraciesError = function () {
		if ($scope.data && $scope.data.accuracies) {
			if ($scope.data.accuracies.length > 0) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	};
	// 是否添加了测量范围
	self.isMeasureScalesError = function () {
		if ($scope.data && $scope.data.measureScales) {
			if ($scope.data.measureScales.length > 0) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	};

	// 是否选择了精确度类别名称
	self.isAccuracyDisplayNameError = function () {
		if ($scope.data) {
			if ($scope.data.accuracyDisplayName && $scope.data.accuracyDisplayName.id && $scope.data.accuracyDisplayName.id > 0) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	};

	// 删除精确度
	self.deleteAccuracy = function (accuracies, index) {
		CommonService.warning(function(success, error){
			AccuracyService.deleteById(accuracies[index].id, function(response){
				if (response.status === 202) {
					accuracies.splice(index, 1);
					success();
				} else {
					error(response);
				}
			});
		});
	};

	self.editEntity = function(object) {
		$scope.currentEditObject = object;
		self.modalEditInstance();
	};

	// 弹出窗口初始化
	self.modalEditInstance = function () {
		$uibModal.open({
			templateUrl: 'views/system/instrumentType/addEntity.html',
			size: 'small',
			controller: 'InstrumentTypeAddCtrlEditObject',
			scope: $scope
		});
	};

	$scope.editEntity = self.editEntity;
	$scope.addEntity = self.addEntity;
	$scope.saveEntity = self.saveEntity;
	$scope.popSpecificationsByIndex = self.popSpecificationsByIndex;
	$scope.popMeasureScalesByIndex = self.popMeasureScalesByIndex;
	$scope.popAccuraciesByIndex = self.popAccuraciesByIndex;
	$scope.downAccuracy = self.downAccuracy;
	$scope.upAccuracy = self.upAccuracy;
	$scope.upMeasureScale = self.upMeasureScale;
	$scope.downMeasureScale = self.downMeasureScale;
	$scope.toggleAccuracy = self.toggleAccuracy;
	$scope.saveAndNew = self.saveAndNew;
	$scope.saveAndClose = self.saveAndClose;
	$scope.togglePurpose = self.togglePurpose;
	$scope.isPurposeChecked = self.isPurposeChecked;
	$scope.isAccuracyChecked = self.isAccuracyChecked;
	$scope.showAddInstrumentTypeFirstLevel = self.showAddInstrumentTypeFirstLevel;
	$scope.submiting = false;
	$scope.isAccuraciesError = self.isAccuraciesError;
	$scope.isMeasureScalesError = self.isMeasureScalesError;
	$scope.isAccuracyDisplayNameError = self.isAccuracyDisplayNameError;
	$scope.console = console;
	$scope.deleteAccuracy = self.deleteAccuracy;
	$scope.edit = self.edit;
	$scope.upSpecification = self.upSpecification;
	$scope.downSpecification = self.downSpecification;
});

/**
 * 弹窗
 * 直接引用父类的$scope。即:$scope.$parent
 * @author panjie
 */
angular.module('webappApp')
.controller('InstrumentTypeAddCtrlEditObject', function ($scope, $uibModalInstance) {
	var self = this;
	self.init = function () {
		$scope.name = $scope.$parent.currentEditObject.value;
		$scope.pinyin = $scope.$parent.currentEditObject.pinyin;
	};
	self.init();

	// 点击确定时，进行数据的保存
	$scope.ok = function () {
		$scope.$parent.currentEditObject.value = $scope.name;
		$scope.$parent.currentEditObject.pinyin = $scope.pinyin;
		$uibModalInstance.close();
	};

	// 点击取消息时，关闭窗口
	$scope.cancel = function () {
		$uibModalInstance.close();
	};
});


/**
 * 弹窗
 * 直接引用父类的$scope。即:$scope.$parent
 * @author panjie
 */
angular.module('webappApp')
.controller('InstrumentTypeAddCtrlModalInstanceCtrl', function ($scope, $uibModalInstance) {
	var self = this;
	self.init = function () {
		$scope.name = '';
		$scope.pinyin = '';
	};
	self.init();

	// 点击确定时，进行数据的保存
	$scope.ok = function () {
		$scope.$parent.saveEntity({name: $scope.name, pinyin: $scope.pinyin}, function () {
			self.init();
			$uibModalInstance.close();
		});
	};

	// 点击取消息时，关闭窗口
	$scope.cancel = function () {
		$uibModalInstance.close();
	};
});
