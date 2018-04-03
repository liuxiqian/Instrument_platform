'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiInstrumentCertificateType
 * @description 器具证书类型
 * # yunzhiInstrumentCertificateType
 */
angular.module('webappApp')
  .directive('yunzhiInstrumentCertificateType', function (InstrumentCheckInfoService, CommonService) {
      return {
          // 独立scope，使各个指令间不互相影响
          scope: {
              // 将指令中的instrumentCertificateType属性，双向绑定到scope.instrumentCertificateType
              ngModel: '='
          },
          // 模板
          templateUrl: 'views/directive/yunzhiInstrumentCertificateType.html',
          restrict: 'EA', // 指令类型，多为E（元素）, A(属性)
          //controller里面$element参数没有用到，暂作删除
          controller: function($scope) {

              $scope.instrumentCertificateTypes = []; // 初始化所有器具证书类型
              $scope.instrumentCertificateType = {}; // 初始化器具证书类型
              $scope.instrumentCertificateType.selected = $scope.ngModel; // 传值。

              var instrumentCertificateType = {id:0, name: '请选择'};
              var index = -1;

              // 获取用户可见的器具证书类型列表
              InstrumentCheckInfoService.getAllInstrumentCertificateTypes(function (data) {
                  $scope.instrumentCertificateTypes = data;
                  $scope.instrumentCertificateTypes.unshift(instrumentCertificateType);

                  if (!angular.equals($scope.ngModel, {})) {
                      index = CommonService.searchByIndexName($scope.ngModel, 'id', $scope.instrumentCertificateTypes);
                  }

                  if (index === -1) {
                      index = 0;          //默认请选择
                  }

                  $scope.instrumentCertificateType.selected = $scope.ngModel = $scope.instrumentCertificateTypes[index];
              });
          },
          link: function postLink(scope) {
              // 监视器具证书类型是否发生变化。如果发生变化，则重置ngModel的值。此时，利用双向数据绑定。将值传回V层
              scope.$watch('instrumentCertificateType', function(newValue) {
                  scope.ngModel = newValue.selected;
              }, true);
          }
      };
  });
