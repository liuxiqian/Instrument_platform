'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiCertificateStatus
 * @description 证书状态
 * # yunzhiCertificateStatus
 */
angular.module('webappApp')
  .directive('yunzhiCertificateStatus', function (InstrumentCheckInfoService, CommonService) {
      return {
          // 独立scope，使各个指令间不互相影响
          scope: {
              // 将指令中的certificateStatus属性，双向绑定到scope.certificateStatus
              ngModel: '='
          },
          // 模板
          templateUrl: 'views/directive/yunzhiCertificateStatus.html',
          restrict: 'EA', // 指令类型，多为E（元素）, A(属性)
          //controller里面$element参数没有用到，暂作删除
          controller: function($scope) {

              $scope.certificateStatuses = []; // 初始化所有证书状态
              $scope.certificateStatus = {}; // 初始化证书状态
              $scope.certificateStatus.selected = $scope.ngModel; // 传值。

              var certificateStatus = {id:0, name: '请选择'};
              var index = -1;

              // 获取用户可见的证书状态列表
              InstrumentCheckInfoService.getAllInstrumentCertificateTypes(function (data) {
                  $scope.certificateStatuses = data;
                  $scope.certificateStatuses.unshift(certificateStatus);

                  if (!angular.equals($scope.ngModel, {})) {
                      index = CommonService.searchByIndexName($scope.ngModel, 'id', $scope.certificateStatuses);
                  }

                  if (index === -1) {
                      index = 0;          //默认请选择
                  }

                  $scope.certificateStatus.selected = $scope.ngModel = $scope.certificateStatuses[index];
              });
          },
          link: function postLink(scope) {
              // 监视证书状态是否发生变化。如果发生变化，则重置ngModel的值。此时，利用双向数据绑定。将值传回V层
              scope.$watch('certificateStatus', function(newValue) {
                  scope.ngModel = newValue.selected;
              }, true);
          }
      };
  });
