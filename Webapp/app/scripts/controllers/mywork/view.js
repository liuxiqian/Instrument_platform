'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MyworkViewCtrl
 * @description 我的工作 查看
 * # MyworkViewCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('MyworkViewCtrl', function($scope, $stateParams, WorkService, CommonService, $controller) {
        var self = this;

        self.init = function(callback) {
            // 继承于查看
            $scope._extend = true;
            angular.extend(self, $controller('MyworkEditCtrl', { $scope: $scope }));
            WorkService.getById($stateParams.id, function(work) {
                $scope.data                           = {};
                $scope.data.currentWorkflowNode       = work.workflowNode; // 当前工作流结点
                $scope.data.nextWork                  = {}; // 下一工作
                $scope.data.nextWork.nextWorkflowNode = {}; // 选择的下一工作流结点
                $scope.data.work                      = work;
                if (callback) {callback();}
            });

        };
       
        self.getExtendViewDirectory = function() {
            return self.getExtendViewDirectoryByAction('view');
        };

        if (!$scope._extend) {
            self.init();
        }
        
        $scope.getExtendViewDirectory = self.getExtendViewDirectory;
        $scope.showBack               = true;
        $scope.showSubmit             = false;
        $scope.back                   = CommonService.back;
    });
