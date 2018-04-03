'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatoryIntegratedEditCtrl
 * @description
 * # MandatoryIntegratedEditCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('MandatoryIntegratedEditCtrl', ['$scope',
    											 '$stateParams',　
    											 'mandatoryInstrumentService',
    											 'CommonService',
    											 function($scope, $stateParams, mandatoryInstrumentService, CommonService) {
        //获取强检器具的id
        var self = this;

        self.mandatoryInstrumentId = $stateParams.id ? parseInt($stateParams.id) : 0;

        //获取强检器具
        mandatoryInstrumentService.findById(self.mandatoryInstrumentId, function(data) {
        	$scope.mandatoryInstrument = data;
        });

        self.save = function() {
	        //更新实体信息
	        mandatoryInstrumentService.updateFixSite(self.mandatoryInstrumentId, $scope.mandatoryInstrument, function() {
	        	CommonService.success('操作成功');
	        });
        };


        //统一暴露
        $scope.save = self.save;
    }]);
