'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MyworkAuditCtrl
 * @description 我的工作 审核
 * # MyworkAuditCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('MyworkAuditCtrl', ['$scope', '$controller', function($scope, $controller) {
        var self = this;
        
        // 继承于查看
        angular.extend(self, $controller('MyworkEditCtrl', { $scope: $scope }));
        
        self.getExtendViewDirectory = function() {
            return self.getExtendViewDirectoryByAction('audit');
        };
        
        
        $scope.getExtendViewDirectory = self.getExtendViewDirectory;
        // 重写按钮显示
        $scope.showBack               = false;
        $scope.showSubmit             = true;
    }]);
