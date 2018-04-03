'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:InstrumentForcedapplyViewCtrl
 * @description
 * # InstrumentForcedapplyViewCtrl
 * 备案申请、查看
 */
angular.module('webappApp')
  .controller('InstrumentForcedapplyViewCtrl', function ($scope, $stateParams, WorkService) {
      var self = this;
      self.init = function () {
          self.load();

      };

      self.load = function () {
          var workId = $stateParams.workId;
          WorkService.getById(workId, function (work) {
              $scope.work = work;
          });
      };
      self.init();
  });
