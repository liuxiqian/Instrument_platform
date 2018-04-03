'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhigenerativedepartment
 * @description
 * # yunzhigenerativedepartment
 * zhuchenshu
 * 强检器具生产部门指令
 */
angular.module('webappApp')
  .directive('yunzhiGenerativeDepartment', function (InstrumentGenerativeDepartment) {
    return {
      templateUrl: 'views/directive/yunzhigenerativedepartment.html',
      restrict: 'E',
      scope: {
      	ngModel:'='
      },
      link: function postLink(scope) {
      		var self = this;

      		self.init = function () {
      			self.getData();               // 请求数据
                self.watch();
      		};

      		self.getData = function() {
      			scope.generativeDepartment = {}; // 生产单位空对象
      			scope.generativeDepartments = [];	// 获取所有的生产单位
      			InstrumentGenerativeDepartment.getAllGenerativeDepartment(function(data) {
      				scope.generativeDepartments = data;
      			});
      		};

      		self.watch = function() {
              scope.$watch('generativeDepartment', function(newValue) {
                  if (newValue.selected && newValue.selected.id) {
                      scope.ngModel = newValue.selected;    // 如果选中，将该值赋给ngModel
                  } else {
                      scope.ngModel = { name: '请选择' };    // 否则，返回一个初始化的名称对象
                  }
              }, true);
      		};

      		self.init();
      }
    };
  });
