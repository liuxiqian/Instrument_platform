'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:StandardFileCheckdetailCtrl
 * @description
 * # StandardFileCheckdetailCtrl (标准器鉴定信息Controller)
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('StandardFileCheckdetailCtrl', ['$scope', 'StandardDeviceCheckDetailService', '$stateParams', 'config', 'UserServer', 'CommonService', function ($scope, StandardDeviceCheckDetailService, $stateParams, config, UserServer, CommonService) {
        var self = this;
        CommonService.initControllerPage(self, $scope);

        self.init = function () {
            self.load();
        };

        //获取所有的分页数据
        self.load = self.reload = function () {
            var standardDevice = {id: $stateParams.standardDeviceId};
            StandardDeviceCheckDetailService.pageAllByStandardDevice(self.generateQueryParams(), standardDevice, function (data) {
                $scope.checkDetailList = data;
            });
        };

        //删除数据
        self.delete = function (index, standardDeviceCheckDetail) {
            CommonService.warning(function (success, error) {
                // 向后台请求删除数据
                StandardDeviceCheckDetailService.delete(standardDeviceCheckDetail.id, function (status) {
                    console.log(status);
                    if (200 === status) {
                        // 从数组中删除数据
                        $scope.checkDetailList.content.splice(index, 1);
                        // 提示用户删除成功
                        success();
                    } else {
                        error('error', '删除失败', '');
                    }
                });
            });

        };

        self.init();
        //统一暴露方法
        $scope.delete = self.delete;
    }]);
