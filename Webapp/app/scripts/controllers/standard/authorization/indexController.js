'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:StandardAuthorizationIndexCtrl
 * @description 标准装置C层
 * # StandardAuthorizationIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('StandardAuthorizationIndexCtrl', ['$scope', 'StandardAuthorizationService', 'config', '$stateParams', 'UserServer', 'InstrumentTypeService', 'CommonService',
        function ($scope, StandardAuthorizationService, config, $stateParams, UserServer, InstrumentTypeService, CommonService) {
            var self = this;
            CommonService.initControllerPage(self, $scope);

            self.init = function () {
                $scope.data = [];
                $scope.params = self.initScopeParams();

                self.load();

                //统一暴露数据
                $scope.log = console.log;
                $scope.showManyDeviceSet = self.showManyDeviceSet;
            };


            // 是否显示多个 标准装置
            self.showManyDeviceSet = function () {
                if ($scope.params.deviceSet.id) {
                    return false;
                } else {
                    return true;
                }
            };

            self.generateQueryParams = function () {
                //整理传入的参数
                return {
                    page: $scope.params.page,
                    size: $scope.params.size,
                    deviceSetId: $scope.params.deviceSetId,
                    districtId: $scope.params.district.id,
                    departmentId: $scope.params.department.id,
                    disciplineId: $scope.params.discipline.id,
                    instrumentTypeFirstLevelId: $scope.params.instrumentTypeFirstLevel.id,
                    instrumentTypeId: $scope.params.instrumentType.id,
                    name: $scope.params.deviceSet.name
                };
            };

            self.load = function () {
                StandardAuthorizationService.pageAllOfCurrentUserBySpecification(self.generateQueryParams(), function (data) {
                    $scope.data = data;
                });
            };

            self.init();

        }]);
