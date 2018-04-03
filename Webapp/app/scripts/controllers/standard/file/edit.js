'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:StandardFileEditCtrl
 * @description
 * # 标准器编辑
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('StandardFileEditCtrl', function ($stateParams, $scope, StandardDeviceService, CommonService) {
        var self = this;
        self.init = function() {
            $scope.standardDevice = $stateParams.standardDevice;
            if (typeof ($scope.standardDevice) === 'undefined') {
                self.loadById($stateParams.id);
            }
        };

        self.loadById = function(id) {
            StandardDeviceService.findById(id)
                .then(function success(response){
                   $scope.standardDevice = response.data;
                }, function error(response){
                    CommonService.httpError(response);
                });
        };

        self.saveAndClose = function () {
            StandardDeviceService.update($scope.standardDevice.id, $scope.standardDevice, function() {
                CommonService.success();
            });
        };

        self.init();
        $scope.saveAndClose = self.saveAndClose;
    });
