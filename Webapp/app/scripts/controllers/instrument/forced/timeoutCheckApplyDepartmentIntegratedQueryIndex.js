'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:InstrumentForcedTimeoutCheckApplyDepartmentQueryIndexCtrl
 * @description 强检器具超期检定备案管理 - 综合查询(管理部门)
 * # InstrumentForcedTimeoutCheckApplyDepartmentQueryIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('InstrumentForcedTimeoutCheckApplyDepartmentQueryIndexCtrl', function($scope, $stateParams, mandatoryInstrumentService) {

        var self = this;
        self.init = function() {
            mandatoryInstrumentService.pageTimeoutCheckDataOfCurrentUser($stateParams, function(data) {
                $scope.data = data;
            });
        };

        self.init();
    });
