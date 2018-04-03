'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:SystemInstrumenttypeManageFirstLevelIndexCtrl
 * @description
 * # SystemInstrumenttypeManageFirstLevelIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('SystemInstrumenttypeManageFirstLevelIndexCtrl', ['$stateParams', '$http', 'InstrumentFirstLevelTypeService', 'CommonService', '$scope', '$state', function ($stateParams, $http, InstrumentFirstLevelTypeService, CommonService, $scope, $state) {
      var self = this;
      self.disciplineId = parseInt($stateParams.disciplineId ? $stateParams.disciplineId : 0); // 学科类别id
      $scope.discipline = { id: self.disciplineId };
      // 定义获取数据的方法
      var showData = function () {
          //获取后台数据
          InstrumentFirstLevelTypeService.getAllByDisciplineId(self.disciplineId, function (response) {
              $scope.datas = response;

              console.log(response);
          });
      };

      // 执行获取数据
      showData();

      //删除方法实现
      self.delete = function (index, id) {
          //提示用户
          CommonService.warning(function (success, error) {
              InstrumentFirstLevelTypeService.delete(id, function (response) {
                  if (204 === response.status) {
                      // 删除此条数据，更新视图
                      $scope.datas.splice(index, 1);
                      success();
                  } else {
                      // 未删除关联实体
                      error('error', '请先删除与其相关联的其它记录', '');
                  }
              });
          });
      };

      $scope.delete = self.delete;

      // 当学科类别发生变化时，重新加载
      $scope.$watch('discipline', function() {
          $stateParams.disciplineId = $scope.discipline.id;
          $state.transitionTo($state.current, $stateParams);
      });

  }]);
